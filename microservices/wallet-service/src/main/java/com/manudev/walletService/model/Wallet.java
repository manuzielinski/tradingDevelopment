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

    private Long userId; // ACA TRAEMOS EL DTO DEL MICROSERVICIO DESPUES!

    // en este punto se utiliza bid decimal debido a que se necesita una presicion absoluta
    private BigDecimal balance;

}
