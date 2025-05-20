package com.manudev.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Data Transfer Object representing detailed information about a coin ( e intencionalmente agregue mas de 120 caracteres, creando un error que el github actions detectara).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinDTO {

    /**
     * Unique identifier of the coin.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Symbol of the coin (e.g., BTC, ETH).
     */
    @JsonProperty("symbol")
    private String symbol;

    /**
     * Name of the coin.
     */
    @JsonProperty("name")
    private String name;

    /**
     * URL of the coin's image.
     */
    @JsonProperty("image")
    private String image;

    /**
     * Current price of the coin.
     */
    @JsonProperty("current_price")
    private double currentPrice;

    /**
     * Market capitalization of the coin.
     */
    @JsonProperty("market_cap")
    private long marketCap;

    /**
     * Market capitalization rank of the coin.
     */
    @JsonProperty("market_cap_rank")
    private int marketCapRank;

    /**
     * Fully diluted valuation.
     */
    @JsonProperty("fully_diluted_valuation")
    private Long fullyDilutedValuation;

    /**
     * Total trading volume.
     */
    @JsonProperty("total_volume")
    private long totalVolume;

    /**
     * Highest price in the last 24 hours.
     */
    @JsonProperty("high_24h")
    private double high24h;

    /**
     * Lowest price in the last 24 hours.
     */
    @JsonProperty("low_24h")
    private double low24h;

    /**
     * Price change in the last 24 hours.
     */
    @JsonProperty("price_change_24h")
    private double priceChange24h;

    /**
     * Percentage price change in the last 24 hours.
     */
    @JsonProperty("price_change_percentage_24h")
    private double priceChangePercentage24h;

    /**
     * Market cap change in the last 24 hours.
     */
    @JsonProperty("market_cap_change_24h")
    private long marketCapChange24h;

    /**
     * Percentage market cap change in the last 24 hours.
     */
    @JsonProperty("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;

    /**
     * Circulating supply of the coin.
     */
    @JsonProperty("circulating_supply")
    private Double circulatingSupply;

    /**
     * Total supply of the coin.
     */
    @JsonProperty("total_supply")
    private Double totalSupply;

    /**
     * Maximum supply of the coin.
     */
    @JsonProperty("max_supply")
    private Double maxSupply;

    /**
     * All-time high price.
     */
    @JsonProperty("ath")
    private double ath;

    /**
     * Percentage change from all-time high.
     */
    @JsonProperty("ath_change_percentage")
    private double athChangePercentage;

    /**
     * Date of all-time high.
     */
    @JsonProperty("ath_date")
    private ZonedDateTime athDate;

    /**
     * All-time low price.
     */
    @JsonProperty("atl")
    private double atl;

    /**
     * Percentage change from all-time low.
     */
    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage;

    /**
     * Date of all-time low.
     */
    @JsonProperty("atl_date")
    private ZonedDateTime atlDate;

    /**
     * Return on investment (ROI).
     */
    @JsonProperty("roi")
    private double roi;

    /**
     * Last updated timestamp.
     */
    @JsonProperty("last_updated")
    private ZonedDateTime lastUpdated;
}
