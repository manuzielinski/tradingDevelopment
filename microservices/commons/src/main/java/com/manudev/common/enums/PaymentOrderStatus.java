package com.manudev.common.enums;

/**
 * Estados posibles para una orden de pago.
 */
public enum PaymentOrderStatus {

    /** El pago está pendiente de procesamiento. */
    PENDING,

    /** El pago se completó con éxito. */
    SUCCESS,

    /** El pago ha fallado. */
    FAILED
}
