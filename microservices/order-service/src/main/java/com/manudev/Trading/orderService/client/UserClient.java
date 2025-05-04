package com.manudev.Trading.orderService.client;

import com.manudev.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/v1/users/profile")
    UserDTO findUserProfileByJwt(@RequestHeader("Authorization") String jwt);
}
