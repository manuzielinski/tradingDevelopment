package com.manudev.walletService.controller;

import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.client.UserClient;
import com.manudev.walletService.model.Wallet;
import com.manudev.walletService.model.Withdrawal;
import com.manudev.walletService.service.WalletService;
import com.manudev.walletService.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WithdrawalController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private WithdrawalService withdrawalService;

//    @Autowired
//    private WalletTransactionService walletTransactionService;

    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(@PathVariable Long amount, @RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        Wallet userWallet = walletService.getUserWallet(userDTO);

        Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, userDTO);
        walletService.addBalance(userWallet,-withdrawal.getAmount());

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PatchMapping("/api/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<?> proceedWithdrawal(@PathVariable Long withdrawalId, @PathVariable boolean accept, @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        Withdrawal withdrawal = withdrawalService.proceedWithdrawal(withdrawalId, accept);

        Wallet userWallet= walletService.getUserWallet(userDTO);

        if(!accept) {
            walletService.addBalance(userWallet,withdrawal.getAmount());
        }
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(@RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);

        List<Withdrawal> withdrawals = withdrawalService.getUsersWithdrawalHistory(userDTO);
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalRequest(@RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);

        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalRequest();
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }
}
