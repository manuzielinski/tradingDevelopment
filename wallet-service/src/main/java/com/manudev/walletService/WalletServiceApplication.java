package com.manudev.walletService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.manudev.walletService.client")
public class WalletServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }
}
