package com.manudev.common.dto;

import lombok.Data;

@Data
public class CreateAssetRequest {
    private UserDTO userDTO;
    private CoinDTO coinDTO;
    private double quantity;
}
