package com.manudev.walletService.client;

import com.manudev.common.dto.PaymentResponse;
import com.manudev.common.enums.PaymentMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    ResponseEntity<PaymentResponse> paymentHandler(@PathVariable PaymentMethod paymentMethod,
                                                   @PathVariable Long amount,
                                                   @RequestHeader("Authorization") String jwt);
}
