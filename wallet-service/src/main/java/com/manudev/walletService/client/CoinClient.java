package com.manudev.walletService.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.manudev.common.dto.CoinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "coin-service")
public interface CoinClient {

    @GetMapping("/coins")
    List<CoinDTO> getCoinList(@RequestParam("page") int page);

    @GetMapping("/coins/{coinId}/chart")
    JsonNode getMarketChart(@PathVariable("coinId") String coinId, @RequestParam("days") int days);

    @GetMapping("/coins/{coinId}")
    JsonNode getCoinDetails(@PathVariable("coinId") String coinId);

    @GetMapping("/coins/search")
    JsonNode searchCoin(@RequestParam("q") String keyword);

    @GetMapping("/coins/top50")
    JsonNode getTop50CoinsByMarketCapRank();

    @GetMapping("/coins/treading")
    JsonNode getTreadingCoins();

    @GetMapping("/coins/{coinId}/raw")
    CoinDTO findById(@PathVariable("coinId") String coinId);
}
