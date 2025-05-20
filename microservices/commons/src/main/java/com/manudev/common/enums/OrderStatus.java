package com.manudev.common.enums;

/**
 * Representa los posibles estados de una orden.
 */
public enum OrderStatus {

    /** Orden creada pero aún no completada ni cancelada. */
    PENDING,

    /** Orden completamente ejecutada. */
    FILLED,

    /** Orden cancelada antes de completarse. */
    CANCELLED,

    /** Orden parcialmente completada. */
    PARTIALLY_FILLED,

    /** Ocurrió un error con la orden. */
    ERROR,

    /** Orden ejecutada con éxito. */
    SUCCESS
}
