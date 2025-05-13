package com.manudev.paymentGatewayService.service;

import com.manudev.common.dto.UserDTO;
import com.manudev.common.enums.PaymentMethod;
import com.manudev.paymentGatewayService.model.PaymentOrder;
import com.manudev.common.dto.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(UserDTO userDTO, Long amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long paymentOrderId) throws Exception;
    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;
    PaymentResponse createRazorpayPaymentLink(UserDTO userDTO, Long amount);
    PaymentResponse createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) throws StripeException;
}
