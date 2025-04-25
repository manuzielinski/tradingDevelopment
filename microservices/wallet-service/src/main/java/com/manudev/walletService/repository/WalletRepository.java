package com.manudev.walletService.repository;

import com.manudev.walletService.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findWalletByUserId(Long userId);
}
