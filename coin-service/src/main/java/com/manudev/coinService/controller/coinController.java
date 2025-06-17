package com.manudev.coinService.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manudev.coinService.service.CoinService;
import com.manudev.common.dto.CoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/coins")
public class coinController {

    private static final Pattern COIN_ID_PATTERN = Pattern.compile("^[a-z0-9\\-]+$");

    @Autowired
    private CoinService coinService;

    @Autowired
    private ObjectMapper objectMapper;

    private void validateCoinId(String coinId) {
        if (!COIN_ID_PATTERN.matcher(coinId).matches()) {
            throw new IllegalArgumentException("Invalid coinId format");
        }
    }

    @GetMapping
    public ResponseEntity<List<CoinDTO>> getCoinList(@RequestParam("page") int page) throws Exception {
        List<CoinDTO> coins = coinService.getCoinList(page);
        return new ResponseEntity<>(coins, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{coinId}/chart")
    public ResponseEntity<JsonNode> getMarketChart(@PathVariable String coinId, @RequestParam("days") int days) throws Exception {
        validateCoinId(coinId);
        String response = coinService.getMarketChart(coinId, days);
        JsonNode jsonNode = objectMapper.readTree(response);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<JsonNode> getCoinDetails(@PathVariable String coinId) throws Exception {
        validateCoinId(coinId);
        CoinDTO coinDTO = coinService.getCoinDetails(coinId);
        JsonNode jsonNode = objectMapper.valueToTree(coinDTO);
        return new ResponseEntity<>(jsonNode, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<JsonNode> searchCoin(@RequestParam("q") String keyword) throws Exception {
        String coin = coinService.searchCoin(keyword);
        JsonNode jsonNode = objectMapper.readTree(coin);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/top50")
    public ResponseEntity<JsonNode> getTop50CoinsByMarketCapRank() throws Exception {
        String coins = coinService.getTop50CoinsByMarketCapRank();
        JsonNode jsonNode = objectMapper.readTree(coins);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/treading")
    public ResponseEntity<JsonNode> getTreadingCoins() throws Exception {
        String coin = coinService.getTrendingCoins();
        JsonNode jsonNode = objectMapper.readTree(coin);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{coinId}/raw")
    public ResponseEntity<CoinDTO> findById(@PathVariable String coinId) throws Exception {
        validateCoinId(coinId);
        CoinDTO coinDTO = coinService.findById(coinId);
        return new ResponseEntity<>(coinDTO, HttpStatus.OK);
    }
}
