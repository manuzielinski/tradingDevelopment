package com.manudev.common.dto;

import lombok.Data;

/**
 * Data Transfer Object representing an asset owned by a user.
 */
@Data
public class AssetDTO {

    /**
     * Unique identifier for the asset.
     */
    private Long assetId;

    /**
     * Quantity of the asset held by the user.
     */
    private double quantity;

    /**
     * Price at which the asset was bought.
     */
    private double buyPrice;

    /**
     * The unique identifier of the coin.
     */
    private String coinId;

    /**
     * Unique identifier of the user who owns the asset.
     */
    private Long userId;

    /**
     * Detailed information about the coin.
     */
    private CoinDTO coinDTO;

    /**
     * Detailed information about the user.
     */
    private UserDTO userDTO;
}
