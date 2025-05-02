package com.manudev.coinService.service;

import java.util.List;
import com.manudev.common.dto.CoinDTO;

public interface CoinService {

    List<CoinDTO> getCoinList(int page) throws Exception;
    String getMarketChart(String coinId, int days) throws Exception;
    CoinDTO getCoinDetails(String coinId) throws Exception;
    CoinDTO findById(String coinId) throws Exception;
    String searchCoin(String keyword) throws Exception;
    String getTop50CoinsByMarketCapRank() throws Exception;
    String getTreadingCoins() throws Exception;
}
