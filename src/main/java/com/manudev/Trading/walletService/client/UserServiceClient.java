package com.manudev.Trading.walletService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", url = "http://localhost:8081") // Cambia la URL si es necesario
public interface UserServiceClient {

    @GetMapping("/api/users/{userId}")
    UserDTO getUserById(@PathVariable("userId") Long userId);
}
