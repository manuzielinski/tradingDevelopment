package com.manudev.Trading.orderService.model;

import com.manudev.common.enums.OrderType;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
