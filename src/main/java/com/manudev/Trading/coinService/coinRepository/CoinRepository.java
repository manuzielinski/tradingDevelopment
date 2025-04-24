package com.manudev.Trading.coinService.coinRepository;

import com.manudev.Trading.coinService.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, String> {
}
