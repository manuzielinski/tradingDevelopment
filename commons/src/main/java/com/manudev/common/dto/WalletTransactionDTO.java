package com.manudev.common.dto;

import com.manudev.common.enums.WalletTransactionTypeDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WalletTransactionDTO {
    private Long walletTransactionId;
    private Long walletId; // Referencia a WalletDTO solo por ID
    private WalletTransactionTypeDTO walletTransactionTypeDTO;  // hacer la conversion hacia el entity original:
    /* dto.setType(WalletTransactionTypeDTO.valueOf(entity.getType().name()));
    entity.setType(WalletTransactionType.valueOf(dto.getType().name()));
    */
    private LocalDate date;
    private String transferId;
    private String purpose;
    private Long amount;
}
