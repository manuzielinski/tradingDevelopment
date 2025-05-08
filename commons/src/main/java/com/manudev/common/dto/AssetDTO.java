package com.manudev.common.dto;

import lombok.Data;

@Data
public class AssetDTO {
    private Long assetId;
    private double quantity;
    private double buyPrice;
    private String coinId;
    private Long userId;
    private CoinDTO coinDTO;
    private UserDTO userDTO;
}
