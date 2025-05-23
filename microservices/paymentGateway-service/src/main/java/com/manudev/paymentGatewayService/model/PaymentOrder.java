package com.manudev.paymentGatewayService.model;

import com.manudev.common.enums.PaymentMethod;
import com.manudev.common.enums.PaymentOrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentOrderId;

    private Long amount;

    private PaymentOrderStatus status;

    private PaymentMethod paymentMethod;

    @NotNull
    private Long userId;
}
