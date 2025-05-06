package com.manudev.common.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WalletDTO {
    private Long walletId;
    private Long userId;
    private BigDecimal balance;
}
