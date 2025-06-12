package com.manudev.coinService.repository;

import com.manudev.coinService.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, String> {
}
