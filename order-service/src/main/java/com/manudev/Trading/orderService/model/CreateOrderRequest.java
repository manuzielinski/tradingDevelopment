package com.manudev.Trading.orderService.model;

import com.manudev.common.enums.OrderType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
