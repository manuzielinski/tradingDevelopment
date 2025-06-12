package com.manudev.paymentGatewayService.client;

import com.manudev.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/method/{userID}")
    UserDTO getUserByID(@PathVariable("userID") Long userID);

    @GetMapping("/method")
    List<UserDTO> listAllUsers();

    @PostMapping("/method")
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PutMapping("/method/{userID}")
    UserDTO updateUser(@PathVariable("userID") Long userID, @RequestBody UserDTO userDTO);

    @DeleteMapping("/method/{userID}")
    String deleteUser(@PathVariable("userID") Long userID);

    @GetMapping("/method/profile")
    UserDTO findUserProfileByJwt(@RequestHeader("Authorization") String jwt);

    @GetMapping("/method/email/{email}")
    UserDTO findUserByEmail(@PathVariable("email") String email);
}
