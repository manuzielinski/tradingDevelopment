package com.manudev.walletService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long walletId;

    private Long userId; // Mapped to UserDTO

    private BigDecimal balance = BigDecimal.ZERO;

}
