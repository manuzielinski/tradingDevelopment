package com.manudev.common.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Data Transfer Object que representa una cartera (wallet) de un usuario.
 */
@Data
public class WalletDTO {

    /**
     * Identificador único de la cartera.
     */
    private Long walletId;

    /**
     * Identificador del usuario propietario de la cartera.
     */
    private Long userId;

    /**
     * Balance actual de la cartera.
     */
    private BigDecimal balance;

    /**
     * Información detallada del usuario propietario.
     */
    private UserDTO userDTO;
}
