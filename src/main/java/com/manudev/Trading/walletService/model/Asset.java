package com.manudev.Trading.walletService.model;

import com.manudev.Trading.coinService.model.Coin;
import com.manudev.Trading.userService.model.UserEntity;
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
    private Long userId;

}
