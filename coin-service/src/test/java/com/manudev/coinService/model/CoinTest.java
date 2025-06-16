package com.manudev.coinService.model;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CoinTest {

    @Test
    void testCoinConstructorAndGetters() {
        ZonedDateTime now = ZonedDateTime.now();

        Coin coin = new Coin(
                "bitcoin",
                "btc",
                "Bitcoin",
                "https://image.url",
                45000.5,
                900000000,
                1,
                1000000000L,
                30000000,
                47000.0,
                44000.0,
                -200.5,
                -0.45,
                -5000000,
                -0.55,
                18000000.0,
                21000000.0,
                21000000.0,
                65000.0,
                -30.0,
                now.minusMonths(6),
                3000.0,
                1400.0,
                now.minusYears(4),
                1.5,
                now
        );

        assertEquals("bitcoin", coin.getId());
        assertEquals("btc", coin.getSymbol());
        assertEquals("Bitcoin", coin.getName());
        assertEquals(45000.5, coin.getCurrentPrice());
        assertEquals(65000.0, coin.getAth());
        assertEquals(3000.0, coin.getAtl());
        assertEquals(now.minusMonths(6), coin.getAthDate());
        assertEquals(now, coin.getLastUpdated());
    }

    @Test
    void testSetters() {
        Coin coin = new Coin();
        coin.setId("ethereum");
        coin.setSymbol("eth");
        coin.setName("Ethereum");

        assertEquals("ethereum", coin.getId());
        assertEquals("eth", coin.getSymbol());
        assertEquals("Ethereum", coin.getName());
    }

    @Test
    void testToStringNotNull() {
        Coin coin = new Coin();
        coin.setId("bitcoin");

        assertNotNull(coin.toString());
        assertTrue(coin.toString().contains("bitcoin"));
    }

    @Test
    void testEqualsAndHashCode() {
        Coin coin1 = new Coin();
        coin1.setId("bitcoin");

        Coin coin2 = new Coin();
        coin2.setId("bitcoin");

        assertEquals(coin1, coin2);
        assertEquals(coin1.hashCode(), coin2.hashCode());
    }
}
