package com.manudev.paymentGatewayService.repository;

import com.manudev.paymentGatewayService.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
}
