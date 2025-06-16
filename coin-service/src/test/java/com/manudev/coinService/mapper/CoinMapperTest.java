package com.manudev.coinService.mapper;

import com.manudev.coinService.model.Coin;
import com.manudev.common.dto.CoinDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CoinMapperTest {

    private final CoinMapper coinMapper = Mappers.getMapper(CoinMapper.class);

    @Test
    void coinToDTO_shouldMapCorrectly() {
        Coin coin = new Coin();
        coin.setId("bitcoin");
        coin.setName("Bitcoin");
        coin.setSymbol("BTC");
        coin.setImage("https://example.com/btc.png");
        coin.setMarketCapRank(1);

        CoinDTO dto = coinMapper.coinToDTO(coin);

        assertNotNull(dto);
        assertEquals("bitcoin", dto.getId());
        assertEquals("Bitcoin", dto.getName());
        assertEquals("BTC", dto.getSymbol());
        assertEquals("https://example.com/btc.png", dto.getImage());
        assertEquals(1, dto.getMarketCapRank());
    }

    @Test
    void dtoToCoin_shouldMapCorrectly() {
        CoinDTO dto = new CoinDTO();
        dto.setId("ethereum");
        dto.setName("Ethereum");
        dto.setSymbol("ETH");
        dto.setImage("https://example.com/eth.png");
        dto.setMarketCapRank(2);

        Coin coin = coinMapper.dtoToCoin(dto);

        assertNotNull(coin);
        assertEquals("ethereum", coin.getId());
        assertEquals("Ethereum", coin.getName());
        assertEquals("ETH", coin.getSymbol());
        assertEquals("https://example.com/eth.png", coin.getImage());
        assertEquals(2, coin.getMarketCapRank());
    }
}
