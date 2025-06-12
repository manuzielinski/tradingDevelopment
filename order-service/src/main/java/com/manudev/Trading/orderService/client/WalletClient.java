package com.manudev.Trading.orderService.client;

import com.manudev.common.dto.UserDTO;
import com.manudev.common.dto.WalletDTO;
import com.manudev.common.dto.WalletTransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "wallet-service") // Este nombre debe coincidir con `spring.application.name` en el microservicio de Wallet
public interface WalletClient {

    @GetMapping("/api/wallet")
    ResponseEntity<WalletDTO> getUserWallet(@RequestHeader("Authorization") String jwt);

    @PutMapping("/api/wallet/{walletId}/transfer")
    ResponseEntity<WalletDTO> walletToWalletTransfer(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable("walletId") Long walletId,
                                                  @RequestBody WalletTransactionDTO req);

    @PutMapping("/api/wallet/order/{orderId}/pay")
    ResponseEntity<WalletDTO> payOrderPayment(@RequestHeader("Authorization") String jwt,
                                           @PathVariable("orderId") Long orderId);
}
