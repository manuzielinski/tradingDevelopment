package com.manudev.Trading.orderService.domain;

public enum OrderStatus {
    PENDING,
    FILLED,
    CANCELLED,
    PARTIALLY_FILLED,
    ERROR,
    SUCCESS
}
