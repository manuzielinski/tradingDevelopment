package com.manudev.walletService.service.impl;

import com.manudev.common.dto.UserDTO;
import com.manudev.common.enums.WithdrawalStatus;
import com.manudev.walletService.model.Withdrawal;
import com.manudev.walletService.repository.WithdrawalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WithdrawalServiceImplTest {

    @Mock
    private WithdrawalRepository withdrawalRepository;

    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setUserId(1L);
    }

    @Test
    void testRequestWithdrawal() {
        Withdrawal expectedWithdrawal = new Withdrawal();
        expectedWithdrawal.setAmount(100L);
        expectedWithdrawal.setUserId(1L);
        expectedWithdrawal.setStatus(WithdrawalStatus.PENDING);

        when(withdrawalRepository.save(any(Withdrawal.class))).thenReturn(expectedWithdrawal);

        Withdrawal result = withdrawalService.requestWithdrawal(100L, userDTO);

        assertNotNull(result);
        assertEquals(100L, result.getAmount());
        assertEquals(1L, result.getUserId());
        assertEquals(WithdrawalStatus.PENDING, result.getStatus());
    }

    @Test
    void testProceedWithdrawalAccepted() throws Exception {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(10L);
        withdrawal.setStatus(WithdrawalStatus.PENDING);

        when(withdrawalRepository.findById(10L)).thenReturn(Optional.of(withdrawal));
        when(withdrawalRepository.save(any(Withdrawal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Withdrawal result = withdrawalService.proceedWithdrawal(10L, true);

        assertEquals(WithdrawalStatus.SUCCESS, result.getStatus());
        assertNotNull(result.getDate());
    }


    @Test
    void testProceedWithdrawalNotFound() {
        when(withdrawalRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            withdrawalService.proceedWithdrawal(99L, true);
        });

        assertEquals("Withdrawal not found", exception.getMessage());
    }

    @Test
    void testGetUsersWithdrawalHistory() {
        Withdrawal w1 = new Withdrawal();
        Withdrawal w2 = new Withdrawal();
        List<Withdrawal> list = Arrays.asList(w1, w2);

        when(withdrawalRepository.findByUserId(1L)).thenReturn(list);

        List<Withdrawal> result = withdrawalService.getUsersWithdrawalHistory(userDTO);

        assertEquals(2, result.size());
    }

    @Test
    void testGetAllWithdrawalRequest() {
        Withdrawal w1 = new Withdrawal();
        Withdrawal w2 = new Withdrawal();
        List<Withdrawal> list = Arrays.asList(w1, w2);

        when(withdrawalRepository.findAll()).thenReturn(list);

        List<Withdrawal> result = withdrawalService.getAllWithdrawalRequest();

        assertEquals(2, result.size());
    }
}
