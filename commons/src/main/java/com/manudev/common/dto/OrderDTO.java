package com.manudev.common.dto;

import com.manudev.common.enums.OrderStatus;
import com.manudev.common.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private OrderType orderType;
    private BigDecimal price;
    private LocalDateTime timestamp;
    private OrderStatus orderStatus;
    private OrderItemDTO orderItem; // CREARLO
}
