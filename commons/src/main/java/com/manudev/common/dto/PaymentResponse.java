package com.manudev.common.dto;

import lombok.Data;

/**
 * DTO que representa la respuesta del sistema de pagos.
 */
@Data
public class PaymentResponse {

    /**
     * URL para realizar el pago.
     */
    private String paymentUrl;
}
