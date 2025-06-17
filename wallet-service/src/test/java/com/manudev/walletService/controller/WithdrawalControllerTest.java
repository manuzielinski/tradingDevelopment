package com.manudev.walletService.controller;

import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.client.UserClient;
import com.manudev.walletService.model.Wallet;
import com.manudev.walletService.model.Withdrawal;
import com.manudev.walletService.service.WalletService;
import com.manudev.walletService.service.WithdrawalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WithdrawalControllerTest {

    @Mock
    private WalletService walletService;

    @Mock
    private UserClient userClient;

    @Mock
    private WithdrawalService withdrawalService;

    @InjectMocks
    private WithdrawalController withdrawalController;

    private UserDTO userDTO;
    private Wallet wallet;

    @BeforeEach
    void setup() {
        userDTO = new UserDTO();
        userDTO.setUserId(1L);

        wallet = new Wallet();
        wallet.setWalletId(1L);
        wallet.setUserId(1L);
        wallet.setBalance(BigDecimal.valueOf(1000));
    }

    @Test
    void testWithdrawalRequest() {
        Withdrawal mockWithdrawal = new Withdrawal();
        mockWithdrawal.setAmount(100L);
        mockWithdrawal.setUserId(1L);

        when(userClient.findUserProfileByJwt("Bearer token")).thenReturn(userDTO);
        when(walletService.getUserWallet(userDTO)).thenReturn(wallet);
        when(withdrawalService.requestWithdrawal(100L, userDTO)).thenReturn(mockWithdrawal);

        ResponseEntity<?> response = withdrawalController.withdrawalRequest(100L, "Bearer token");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWithdrawal, response.getBody());

        verify(walletService).addBalance(wallet, -100L);
    }


    @Test
    void testProceedWithdrawal_Rejected() throws Exception {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(10L);
        withdrawal.setUserId(1L);
        withdrawal.setAmount(200L);

        when(userClient.findUserProfileByJwt("Bearer token")).thenReturn(userDTO);
        when(withdrawalService.proceedWithdrawal(10L, false)).thenReturn(withdrawal);
        when(walletService.getUserWallet(userDTO)).thenReturn(wallet);

        ResponseEntity<?> response = withdrawalController.proceedWithdrawal(10L, false, "Bearer token");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(withdrawal, response.getBody());

        verify(walletService).addBalance(wallet, 200L);
    }

    @Test
    void testGetWithdrawalHistory() {
        Withdrawal w1 = new Withdrawal();
        Withdrawal w2 = new Withdrawal();
        List<Withdrawal> mockList = Arrays.asList(w1, w2);

        when(userClient.findUserProfileByJwt("Bearer token")).thenReturn(userDTO);
        when(withdrawalService.getUsersWithdrawalHistory(userDTO)).thenReturn(mockList);

        ResponseEntity<List<Withdrawal>> response = withdrawalController.getWithdrawalHistory("Bearer token");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetAllWithdrawalRequest() {
        Withdrawal w1 = new Withdrawal();
        Withdrawal w2 = new Withdrawal();
        List<Withdrawal> mockList = Arrays.asList(w1, w2);

        when(userClient.findUserProfileByJwt("Bearer token")).thenReturn(userDTO);
        when(withdrawalService.getAllWithdrawalRequest()).thenReturn(mockList);

        ResponseEntity<List<Withdrawal>> response = withdrawalController.getAllWithdrawalRequest("Bearer token");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
}
