package com.manudev.paymentGatewayService.controller;

import com.manudev.common.dto.UserDTO;
import com.manudev.paymentGatewayService.client.UserClient;
import com.manudev.common.enums.PaymentMethod;
import com.manudev.paymentGatewayService.model.PaymentOrder;
import com.manudev.common.dto.PaymentResponse;
import com.manudev.paymentGatewayService.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(@PathVariable PaymentMethod paymentMethod,
                                                          @PathVariable Long amount,
                                                          @RequestHeader("Authorization") String jwt) throws StripeException {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        PaymentResponse paymentResponse;

        PaymentOrder order = paymentService.createOrder(userDTO,amount,paymentMethod);

        if(paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            paymentResponse = paymentService.createRazorpayPaymentLink(userDTO,amount);
        }
        else {
            paymentResponse = paymentService.createStripePaymentLink(userDTO,amount,order.getPaymentOrderId());
        }
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
}
