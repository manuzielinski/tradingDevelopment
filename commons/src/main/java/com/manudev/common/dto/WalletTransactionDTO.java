package com.manudev.common.dto;

import com.manudev.common.enums.WalletTransactionTypeDTO;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object que representa una transacción en una cartera (wallet).
 */
@Data
public class WalletTransactionDTO {

    /**
     * Identificador único de la transacción.
     */
    private Long walletTransactionId;

    /**
     * Identificador de la cartera asociada a esta transacción.
     */
    private Long walletId;

    /**
     * Tipo de transacción realizada en la cartera.
     * <p>
     * Conversión ejemplo entre DTO y entidad:
     * <pre>
     * dto.setType(WalletTransactionTypeDTO.valueOf(entity.getType().name()));
     * entity.setType(WalletTransactionType.valueOf(dto.getType().name()));
     * </pre>
     * </p>
     */
    private WalletTransactionTypeDTO walletTransactionTypeDTO;

    /**
     * Fecha en la que se realizó la transacción.
     */
    private LocalDate date;

    /**
     * Identificador de transferencia relacionado a la transacción.
     */
    private String transferId;

    /**
     * Propósito o descripción de la transacción.
     */
    private String purpose;

    /**
     * Monto involucrado en la transacción.
     */
    private Long amount;
}
