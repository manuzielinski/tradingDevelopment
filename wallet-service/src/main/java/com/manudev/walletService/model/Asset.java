package com.manudev.walletService.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assetId;

    private double quantity;
    private double buyPrice;

    // Solo almacenas el ID de la moneda (Coin) relacionada
    @Column(name = "coin_id")
    private String coinId;

    @Column(name = "user_id")
    private Long userId;
}
