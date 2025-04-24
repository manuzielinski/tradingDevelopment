package com.manudev.Trading.walletService.repository;

import com.manudev.Trading.walletService.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findWalletByUserId(Long userId);
}
