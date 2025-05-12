package com.manudev.walletService.service;

import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal requestWithdrawal(Long amount, UserDTO userDTO);
    Withdrawal proceedWithdrawal(Long withdrawalId, boolean accept) throws Exception;
    List<Withdrawal> getUsersWithdrawalHistory(UserDTO userDTO);
    List<Withdrawal> getAllWithdrawalRequest();
}
