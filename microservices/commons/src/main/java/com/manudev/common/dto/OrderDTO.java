package com.manudev.common.dto;

import com.manudev.common.enums.OrderStatus;
import com.manudev.common.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object representing an order placed by a user.
 */
@Data
public class OrderDTO {

    /**
     * Unique identifier of the order.
     */
    private Long orderId;

    /**
     * Identifier of the user who placed the order.
     */
    private Long userId;

    /**
     * Type of the order (e.g., BUY or SELL).
     */
    private OrderType orderType;

    /**
     * Price at which the order was placed.
     */
    private BigDecimal price;

    /**
     * Timestamp indicating when the order was created.
     */
    private LocalDateTime timestamp;

    /**
     * Current status of the order.
     */
    private OrderStatus orderStatus;

    /**
     * The item associated with the order.
     */
    private OrderItemDTO orderItem;
}
