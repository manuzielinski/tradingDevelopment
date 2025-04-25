package com.manudev.walletService.service;

import com.manudev.walletService.model.Wallet;

public interface WalletService {
    Wallet getUserWallet (UserDTO userDTO);
    Wallet addBalance(Wallet wallet, Long amount);
    Wallet findWalletById(Long walletId) throws Exception;
    Wallet WalletToWalletTransfer(UserEntity sender, Wallet receiver, Long amount) throws Exception;
    Wallet payOrderPayment(Order order, UserEntity user) throws Exception;

}
