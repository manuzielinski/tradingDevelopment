package com.manudev.walletService.model;

import com.manudev.walletService.domain.WalletTransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletTransactionId;

    @ManyToOne
    private Wallet wallet;

    private WalletTransactionType type;

    private LocalDate date;

    private String transferId;

    private String purpose;

    private Long amount;

}
