package com.manudev.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long orderItemId;
    private double quantity;
    private CoinDTO coinDTO;
    private double buyPrice;
    private double sellPrice;
    private Long orderId;
}
