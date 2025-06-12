package com.manudev.userService.service.impl;

import com.manudev.userService.model.PaymentDetails;
import com.manudev.userService.model.UserEntity;
import com.manudev.userService.repository.PaymentDetailsRepository;
import com.manudev.userService.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc, String bankName, UserEntity userEntity) {
        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setAccountNumber(accountNumber);
        paymentDetails.setAccountHolderName(accountHolderName);
        paymentDetails.setIfsc(ifsc);
        paymentDetails.setBankName(bankName);
        paymentDetails.setUserEntity(userEntity);

        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public PaymentDetails getUserPaymentDetails(UserEntity userEntity) {
        return paymentDetailsRepository.findByUserId(userEntity.getUserID());
    }
}
