package com.manudev.paymentGatewayService.service.impl;

import com.manudev.common.dto.UserDTO;
import com.manudev.common.enums.PaymentMethod;
import com.manudev.common.enums.PaymentOrderStatus;
import com.manudev.paymentGatewayService.model.PaymentOrder;
import com.manudev.paymentGatewayService.repository.PaymentOrderRepository;
import com.manudev.common.dto.PaymentResponse;
import com.manudev.paymentGatewayService.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String apikey;

    @Value("${razorpay.api.secret}")
    private String apiSecretKey;

    @Override
    public PaymentOrder createOrder(UserDTO userDTO, Long amount, PaymentMethod paymentMethod) {

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setUserId(userDTO.getUserID());
        paymentOrder.setAmount(amount);
        paymentOrder.setPaymentMethod(paymentMethod);

        return paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long paymentOrderId) throws Exception {
        return paymentOrderRepository.findById(paymentOrderId)
                .orElseThrow(() -> new Exception("payment order not found"));
    }

    @Override
    public Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException {
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){
            if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)){
                RazorpayClient razorpay = new RazorpayClient(apikey,apiSecretKey);
                Payment payment = razorpay.payments.fetch(paymentId);

                Integer amount = payment.get("amount");
                String status = payment.get("status");

                if(status.equals("captured")) {
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    return true;
                }
                paymentOrder.setStatus(PaymentOrderStatus.FAILED);
                paymentOrderRepository.save(paymentOrder);
                return false;
            }
            paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
            paymentOrderRepository.save(paymentOrder);
            return true;
        }
        return false;
    }

    @Override
    public PaymentResponse createRazorpayPaymentLink(UserDTO userDTO, Long amount) {

        Long Amount = amount*100;

        try {
            // instance to razorpay client with key id and key secret
            RazorpayClient razorpay = new RazorpayClient(apikey, apiSecretKey);

            // create json object with the payment link request parameters
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");

            // create json object with the customer details
            JSONObject customer = new JSONObject();
            customer.put("name", userDTO.getUsername());

            customer.put("email", userDTO.getEmail());
            paymentLinkRequest.put("customer", customer);

            // create json objecti with the notification settings
            JSONObject notify = new JSONObject();
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            // set reminder settings
            paymentLinkRequest.put("reminder_enable", true);

            // set the callbar url and method
            paymentLinkRequest.put("callback_url", "http://localhost:5173/wallet");
            paymentLinkRequest.put("callback_method", "get");

            // create payment link using paymentLink.create()
            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            PaymentResponse response = new PaymentResponse();
            response.setPayment_url(paymentLinkUrl);

            return response;

        } catch (RazorpayException e) {
            System.out.println("Error creating payment link: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaymentResponse createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/wallet?order_id="+orderId)
                .setCancelUrl("http://localhost:5173/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount*100)
                                .setProductData(SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .ProductData
                                        .builder()
                                        .setName("top up wallet")
                                                .build()
                                        ).build()
                        ).build()
                ).build();

        Session session = Session.create(params);

        System.out.println("session _____ " + session);

        PaymentResponse response = new PaymentResponse();
        response.setPayment_url(session.getUrl());

        return response;
    }
}
