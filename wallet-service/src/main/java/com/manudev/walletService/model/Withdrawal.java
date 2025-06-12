package com.manudev.walletService.model;

import com.manudev.common.enums.WithdrawalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long withdrawalId;

    private WithdrawalStatus status;

    private Long amount;

    @Column(name = "user_id")
    private Long userId;

    private LocalDateTime date= LocalDateTime.now();
}
