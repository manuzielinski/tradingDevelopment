package com.manudev.walletService.service;

import com.manudev.common.dto.OrderDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.model.Wallet;

public interface WalletService {
    Wallet getUserWallet (UserDTO userDTO);
    Wallet addBalance(Wallet wallet, Long amount);
    Wallet findWalletById(Long walletId) throws Exception;
    Wallet WalletToWalletTransfer(UserDTO sender, Wallet receiver, Long amount) throws Exception;
    Wallet payOrderPayment(OrderDTO orderDTO, UserDTO userDTO) throws Exception;

}
