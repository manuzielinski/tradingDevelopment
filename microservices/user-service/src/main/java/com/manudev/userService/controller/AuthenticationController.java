package com.manudev.userService.controller;

import com.manudev.Trading.userService.dto.AuthCreateUserDTO;
import com.manudev.Trading.userService.dto.AuthLoginRequestDTO;
import com.manudev.Trading.userService.dto.AuthResponseDTO;
import com.manudev.Trading.userService.service.impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid AuthCreateUserDTO authCreateUserDTO){
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUserDTO), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponseDTO> Login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}
