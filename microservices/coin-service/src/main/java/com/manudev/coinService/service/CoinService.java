package com.manudev.coinService.service;

import com.manudev.common.dto.CoinDTO;

import java.util.List;

public interface CoinService {

    List<CoinDTO> getCoinList(int page) throws Exception;
    String getMarketChart(String coinId, int days) throws Exception;
    CoinDTO getCoinDetails(String coinId) throws Exception;
    CoinDTO findById(String coinId) throws Exception;
    String searchCoin(String keyword) throws Exception;
    String getTop50CoinsByMarketCapRank() throws Exception;
    String getTrendingCoins() throws Exception;
}
