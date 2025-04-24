package com.manudev.Trading.OrderService.domain;

public enum OrderStatus {
    PENDING,
    FILLED,
    CANCELLED,
    PARTIALLY_FILLED,
    ERROR,
    SUCCESS
}
