package com.manudev.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing an item within an order.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    /**
     * Unique identifier of the order item.
     */
    private Long orderItemId;

    /**
     * Quantity of the coin in this order item.
     */
    private double quantity;

    /**
     * Details of the coin associated with this order item.
     */
    private CoinDTO coinDTO;

    /**
     * Price at which the coin was bought.
     */
    private double buyPrice;

    /**
     * Price at which the coin was sold.
     */
    private double sellPrice;

    /**
     * Identifier of the order to which this item belongs.
     */
    private Long orderId;
}
