package com.manudev.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinDTO {

    private String id;
    private String symbol;
    private String name;
    private String image;

    private double currentPrice;
    private long marketCap;
    private int marketCapRank;

    private double priceChangePercentage24h;
    private double ath;
    private double atl;

    private ZonedDateTime lastUpdated;
}
