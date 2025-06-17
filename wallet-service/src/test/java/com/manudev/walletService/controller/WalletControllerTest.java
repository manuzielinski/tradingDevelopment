package com.manudev.walletService.controller;

import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.client.OrderClient;
import com.manudev.walletService.client.UserClient;
import com.manudev.walletService.model.Wallet;
import com.manudev.walletService.model.WalletTransaction;
import com.manudev.walletService.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @Mock
    private WalletService walletService;

    @Mock
    private UserClient userClient;

    @Mock
    private OrderClient orderClient;

    @InjectMocks
    private WalletController walletController;

    private UserDTO mockUser;
    private Wallet mockWallet;

    @BeforeEach
    void setUp() {
        mockUser = new UserDTO();
        mockUser.setUserId(1L);

        mockWallet = new Wallet();
        mockWallet.setWalletId(1L);
        mockWallet.setUserId(1L);
        mockWallet.setBalance(BigDecimal.valueOf(1000.0));
    }

    @Test
    void testGetUserWallet() {
        when(userClient.findUserProfileByJwt("Bearer token")).thenReturn(mockUser);
        when(walletService.getUserWallet(mockUser)).thenReturn(mockWallet);

        ResponseEntity<Wallet> response = walletController.getUserWallet("Bearer token");

        assertEquals(202, response.getStatusCodeValue());
        assertEquals(mockWallet, response.getBody());
    }

    @Test
    void testWalletToWalletTransfer() throws Exception {
        WalletTransaction tx = new WalletTransaction();
        tx.setAmount((long) 100.0);

        Wallet receiverWallet = new Wallet();
        receiverWallet.setWalletId(2L);
        receiverWallet.setUserId(2L);
        receiverWallet.setBalance(BigDecimal.valueOf(500.0));

        Wallet updatedWallet = new Wallet();
        updatedWallet.setWalletId(1L);
        updatedWallet.setUserId(1L);
        updatedWallet.setBalance(BigDecimal.valueOf(900.0)); // Supongamos que se descontaron 100

        when(userClient.findUserProfileByJwt("Bearer token")).thenReturn(mockUser);
        when(walletService.findWalletById(2L)).thenReturn(receiverWallet);
        when(walletService.WalletToWalletTransfer(mockUser, receiverWallet, (long) 100.0)).thenReturn(updatedWallet);

        ResponseEntity<Wallet> response = walletController.walletToWalletTransfer("Bearer token", 2L, tx);

        assertEquals(202, response.getStatusCodeValue());
        assertEquals(updatedWallet, response.getBody());
        assertEquals(900.0, response.getBody().getBalance());
    }


}
