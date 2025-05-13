package com.manudev.userService.repository;

import com.manudev.userService.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

    PaymentDetails findByUserId(Long userId);
}
