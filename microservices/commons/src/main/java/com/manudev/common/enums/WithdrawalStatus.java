package com.manudev.common.enums;

/**
 * Estado de una solicitud de retiro.
 */
public enum WithdrawalStatus {

    /** La solicitud de retiro está pendiente de procesamiento. */
    PENDING,

    /** La solicitud de retiro fue procesada con éxito. */
    SUCCESS,

    /** La solicitud de retiro fue declinada o rechazada. */
    DECLINED
}
