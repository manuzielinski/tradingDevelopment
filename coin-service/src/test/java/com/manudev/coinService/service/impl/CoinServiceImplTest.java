package com.manudev.coinService.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manudev.coinService.mapper.CoinMapper;
import com.manudev.coinService.model.Coin;
import com.manudev.coinService.repository.CoinRepository;
import com.manudev.common.dto.CoinDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoinServiceImplTest {

    @InjectMocks
    private CoinServiceImpl coinService;

    @Mock
    private CoinRepository coinRepository;

    @Mock
    private CoinMapper coinMapper;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private RestTemplate restTemplate;

    private Coin dummyCoin;
    private CoinDTO dummyCoinDTO;

    @BeforeEach
    void setUp() {
        dummyCoin = new Coin();
        dummyCoin.setId("bitcoin");
        dummyCoin.setName("Bitcoin");
        dummyCoin.setSymbol("BTC");

        dummyCoinDTO = new CoinDTO();
        dummyCoinDTO.setId("bitcoin");
        dummyCoinDTO.setName("Bitcoin");
        dummyCoinDTO.setSymbol("BTC");
    }

    @Test
    void getCoinList_shouldReturnListOfCoinDTOs() throws Exception {
        String jsonResponse = "[{\"id\":\"bitcoin\",\"name\":\"Bitcoin\",\"symbol\":\"btc\"}]";
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=10&page=1";

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(jsonResponse));
        when(objectMapper.readValue(eq(jsonResponse), ArgumentMatchers.<TypeReference<List<Coin>>>any()))
                .thenReturn(List.of(dummyCoin));
        when(coinMapper.coinToDTO(dummyCoin)).thenReturn(dummyCoinDTO);

        List<CoinDTO> result = coinService.getCoinList(1);

        assertEquals(1, result.size());
        assertEquals("bitcoin", result.get(0).getId());
    }

    @Test
    void getMarketChart_shouldReturnJsonString() throws Exception {
        String coinId = "bitcoin";
        int days = 7;
        String expected = "{\"prices\": [[1,2],[3,4]]}";

        String url = "https://api.coingecko.com/api/v3/coins/" + coinId + "/market_chart?vs_currency=usd&days=" + days;

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(expected));

        String result = coinService.getMarketChart(coinId, days);

        assertEquals(expected, result);
    }

    @Test
    void findById_shouldReturnCoinDTO_whenCoinExists() throws Exception {
        when(coinRepository.findById("bitcoin")).thenReturn(Optional.of(dummyCoin));
        when(coinMapper.coinToDTO(dummyCoin)).thenReturn(dummyCoinDTO);

        CoinDTO result = coinService.findById("bitcoin");

        assertNotNull(result);
        assertEquals("bitcoin", result.getId());
    }

    @Test
    void findById_shouldThrowException_whenCoinNotFound() {
        when(coinRepository.findById("ethereum")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> coinService.findById("ethereum"));

        assertEquals("Coin not found", exception.getMessage());
    }

    @Test
    void searchCoin_shouldReturnApiResponse() throws Exception {
        String keyword = "bitcoin";
        String expectedResponse = "{\"coins\":[{\"id\":\"bitcoin\",\"name\":\"Bitcoin\"}]}";
        String url = "https://api.coingecko.com/api/v3/search?query=" + keyword;

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        String result = coinService.searchCoin(keyword);

        assertEquals(expectedResponse, result);
    }


    @Test
    void getCoinDetails_shouldHandleNullMarketData() throws Exception {
        String coinId = "bitcoin";
        String jsonResponse = """
        {
            "id": "bitcoin",
            "name": "Bitcoin",
            "symbol": "BTC",
            "image": {"large": "https://example.com/btc.png"}
        }
    """;

        String url = "https://api.coingecko.com/api/v3/coins/" + coinId;

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(jsonResponse));
        when(objectMapper.readTree(jsonResponse)).thenReturn(new ObjectMapper().readTree(jsonResponse));
        when(coinMapper.coinToDTO(any())).thenReturn(dummyCoinDTO);

        CoinDTO result = coinService.getCoinDetails(coinId);

        assertNotNull(result);
        verify(coinRepository).save(any(Coin.class));
    }

    @Test
    void getTop50CoinsByMarketCapRank_shouldReturnJson() throws Exception {
        String expected = "[{\"id\":\"bitcoin\"}]";
        String url = "https://api.coingecko.com/api/v3/coins/markets/vs_currency=usd&per_page=50&page=1";

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(expected));

        String result = coinService.getTop50CoinsByMarketCapRank();

        assertEquals(expected, result);
    }



}
