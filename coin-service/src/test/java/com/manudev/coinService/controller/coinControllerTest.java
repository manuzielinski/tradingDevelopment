package com.manudev.coinService.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manudev.coinService.service.CoinService;
import com.manudev.common.dto.CoinDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CoinControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private coinController coinController;

    @Mock
    private CoinService coinService;

    @Mock
    private ObjectMapper objectMapper;

    private CoinDTO dummyCoin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(coinController).build();

        dummyCoin = new CoinDTO();
        dummyCoin.setId("bitcoin");
        dummyCoin.setName("Bitcoin");
        dummyCoin.setSymbol("BTC");
        dummyCoin.setMarketCapRank(1);
    }

    @Test
    void getCoinList_returnsList() throws Exception {
        when(coinService.getCoinList(1)).thenReturn(List.of(dummyCoin));

        mockMvc.perform(get("/coins?page=1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].id").value("bitcoin"))
                .andExpect(jsonPath("$[0].name").value("Bitcoin"))
                .andExpect(jsonPath("$[0].symbol").value("BTC"));
    }

    @Test
    void getCoinDetails_returnsJsonNode() throws Exception {
        when(coinService.getCoinDetails("bitcoin")).thenReturn(dummyCoin);

        JsonNode jsonNode = new ObjectMapper().valueToTree(dummyCoin);
        when(objectMapper.valueToTree(dummyCoin)).thenReturn(jsonNode);

        mockMvc.perform(get("/coins/bitcoin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("bitcoin"))
                .andExpect(jsonPath("$.name").value("Bitcoin"));
    }

    @Test
    void getMarketChart_returnsJsonNode() throws Exception {
        String response = "{\"prices\": [[1,2],[3,4]]}";
        JsonNode jsonNode = new ObjectMapper().readTree(response);

        when(coinService.getMarketChart("bitcoin", 7)).thenReturn(response);
        when(objectMapper.readTree(response)).thenReturn(jsonNode);

        mockMvc.perform(get("/coins/bitcoin/chart?days=7"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.prices").isArray());
    }

    @Test
    void searchCoin_returnsJsonNode() throws Exception {
        String response = "{\"coins\":[]}";
        JsonNode jsonNode = new ObjectMapper().readTree(response);

        when(coinService.searchCoin("bit")).thenReturn(response);
        when(objectMapper.readTree(response)).thenReturn(jsonNode);

        mockMvc.perform(get("/coins/search?q=bit"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.coins").isArray());
    }

    @Test
    void getTop50Coins_returnsJsonNode() throws Exception {
        String response = "[{\"id\": \"bitcoin\"}]";
        JsonNode jsonNode = new ObjectMapper().readTree(response);

        when(coinService.getTop50CoinsByMarketCapRank()).thenReturn(response);
        when(objectMapper.readTree(response)).thenReturn(jsonNode);

        mockMvc.perform(get("/coins/top50"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].id").value("bitcoin"));
    }

    @Test
    void getTrendingCoins_returnsJsonNode() throws Exception {
        String response = "{\"coins\": [{\"id\": \"bitcoin\"}]}";
        JsonNode jsonNode = new ObjectMapper().readTree(response);

        when(coinService.getTrendingCoins()).thenReturn(response);
        when(objectMapper.readTree(response)).thenReturn(jsonNode);

        mockMvc.perform(get("/coins/treading"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.coins").isArray());
    }

    @Test
    void findById_returnsRawCoinDTO() throws Exception {
        when(coinService.findById("bitcoin")).thenReturn(dummyCoin);

        mockMvc.perform(get("/coins/bitcoin/raw"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("bitcoin"))
                .andExpect(jsonPath("$.name").value("Bitcoin"));
    }
}
