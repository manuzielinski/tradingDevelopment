package com.manudev.Trading.walletService.controller;

import com.manudev.Trading.OrderService.service.OrderService;
import com.manudev.Trading.OrderService.model.Order;
import com.manudev.Trading.walletService.model.Wallet;
import com.manudev.Trading.walletService.model.WalletTransaction;
import com.manudev.Trading.walletService.service.WalletService;
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
    private OrderService orderService;

    @GetMapping("/api/wallet")
    public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt){
        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(userDTO);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/${walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long walletId,
                                                         @RequestBody WalletTransaction req) throws Exception {

        UserDTO senderUserDTO = userService.findUserProfileByJwt(jwt);
        UserEntity userEntity = userMapper.dtoToUser(senderUserDTO);
        Wallet receiverWallet = walletService.findWalletById(walletId);
        Wallet wallet = walletService.WalletToWalletTransfer(userEntity, receiverWallet, req.getAmount());

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/order/${orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long orderId) throws Exception {

        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        UserEntity userEntity = userMapper.dtoToUser(userDTO);
        Order order = orderService.getOrderByID(orderId);
        Wallet wallet = walletService.payOrderPayment(order,userEntity);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }
}
