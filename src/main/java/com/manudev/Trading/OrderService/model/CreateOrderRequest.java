package com.manudev.Trading.OrderService.model;

import com.manudev.Trading.OrderService.domain.OrderType;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
