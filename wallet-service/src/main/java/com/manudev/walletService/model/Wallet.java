package com.manudev.walletService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long walletId;

    @NotNull
    private Long userId; // Mapped to UserDTO

    private BigDecimal balance = BigDecimal.ZERO;

}
