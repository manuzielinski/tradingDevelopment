package com.manudev.Trading.orderService.service;

import com.manudev.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserClient {

    @GetMapping("/{userID}")
    UserDTO getUserById(@PathVariable("userID") Long userID);

    @GetMapping
    public List<UserDTO> listAllUsers();

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO);

    @PutMapping("/{userID}")
    public UserDTO updateUser(@PathVariable Long userID, @RequestBody UserDTO userDTO);

    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable Long userID);

    @GetMapping("/profile")
    public UserDTO getUserProfileByJwt(@RequestHeader("Authorization") String jwt);

    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email);
}

