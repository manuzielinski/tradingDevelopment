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

    @ManyToOne
    private Coin coin;

    @Column(name = "user_id")
    private Long userId;    // ACA PONER EL DTO NUEVO QUE SE COMPARTE ENTRE LOS MS

}
