package com.manudev.userService.service;

import com.manudev.userService.model.PaymentDetails;
import com.manudev.userService.model.UserEntity;

public interface PaymentDetailsService {

    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc, String bankName, UserEntity userEntity);

    public PaymentDetails getUserPaymentDetails(UserEntity userEntity);
}
