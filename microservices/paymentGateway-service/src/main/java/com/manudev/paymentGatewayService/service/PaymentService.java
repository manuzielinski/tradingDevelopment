package com.manudev.paymentGatewayService.service;

import com.manudev.common.dto.UserDTO;
import com.manudev.paymentGatewayService.domain.PaymentMethod;
import com.manudev.paymentGatewayService.model.PaymentOrder;
import com.manudev.paymentGatewayService.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(UserDTO userDTO, Long amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long paymentOrderId) throws Exception;
    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;
    PaymentResponse createRazorpayPaymentLing(UserDTO userDTO, Long amount);
    PaymentResponse createStripePaymentLing(UserDTO userDTO, Long amount, Long orderId) throws StripeException;
}
