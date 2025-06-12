package com.manudev.userService.controller;

import com.manudev.common.dto.UserDTO;
import com.manudev.userService.mapper.UserMapper;
import com.manudev.userService.model.PaymentDetails;
import com.manudev.userService.model.UserEntity;
import com.manudev.userService.service.PaymentDetailsService;
import com.manudev.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentDetailsController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/payment-details")
    public ResponseEntity<PaymentDetails> addPaymentDetails(@RequestBody PaymentDetails paymentDetailsRequest, @RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        UserEntity userEntity = userMapper.dtoToUser(userDTO);
        PaymentDetails paymentDetails = paymentDetailsService.addPaymentDetails(
                paymentDetailsRequest.getAccountNumber(),
                paymentDetailsRequest.getAccountHolderName(),
                paymentDetailsRequest.getIfsc(),
                paymentDetailsRequest.getBankName(),
                userEntity
        );
        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/payment-details")
    public ResponseEntity<PaymentDetails> getUsersPaymentDetails(@RequestHeader("Authorization") String jwt) {
        UserDTO userDTO = userService.findUserProfileByJwt(jwt);
        UserEntity userEntity = userMapper.dtoToUser(userDTO);

        PaymentDetails paymentDetails = paymentDetailsService.getUserPaymentDetails(userEntity);
        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }
}
