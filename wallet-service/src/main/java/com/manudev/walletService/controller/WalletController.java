package com.manudev.walletService.controller;

import com.manudev.common.dto.OrderDTO;
import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.client.OrderClient;
import com.manudev.walletService.client.UserClient;
import com.manudev.walletService.model.Wallet;
import com.manudev.walletService.model.WalletTransaction;
import com.manudev.walletService.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderClient orderClient;

    // GET /api/wallet
    @GetMapping
    public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(userDTO);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    // PUT /api/wallet/{walletId}/transfer
    @PutMapping("/{walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long walletId,
                                                         @RequestBody WalletTransaction req) throws Exception {
        UserDTO senderUserDTO = userClient.findUserProfileByJwt(jwt);
        Wallet receiverWallet = walletService.findWalletById(walletId);
        Wallet wallet = walletService.WalletToWalletTransfer(senderUserDTO, receiverWallet, req.getAmount());
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    // PUT /api/wallet/order/{orderId}/pay
    @PutMapping("/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Long orderId) throws Exception {
        UserDTO userDTO = userClient.findUserProfileByJwt(jwt);
        OrderDTO orderDTO = orderClient.getOrderById(jwt, orderId);
        Wallet wallet = walletService.payOrderPayment(orderDTO, userDTO);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }
}
