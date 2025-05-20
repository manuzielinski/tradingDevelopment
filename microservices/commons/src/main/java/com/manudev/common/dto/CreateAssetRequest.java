package com.manudev.common.dto;

import lombok.Data;

/**
 * Request object used to create a new asset for a user.
 */
@Data
public class CreateAssetRequest {

    /**
     * User data associated with the asset.
     */
    private UserDTO userDTO;

    /**
     * Coin data of the asset to be added.
     */
    private CoinDTO coinDTO;

    /**
     * Quantity of the coin to be recorded in the asset.
     */
    private double quantity;
}
