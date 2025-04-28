package com.manudev.userService.controller;

import com.manudev.userService.model.UserEntity;
import com.manudev.userService.repository.UserRepository;
import com.manudev.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/method")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{userID}")
    public UserEntity getUserByID(@PathVariable Long userID) {
        return userService.getUserByID(userID);
    }

    @GetMapping
    public List<UserEntity> listAllUsers() {
        return userService.listAllUsers();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @PutMapping("/{userID}")
    public UserEntity updateUser(@PathVariable Long userID, @RequestBody UserEntity userEntity) {
        return userService.updateUser(userID, userEntity);
    }

    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable Long userID) {
        userService.deleteUser(userID);
        return "Se ha eliminado el usuario" + userID;
    }

    @GetMapping("/profile")
    public UserEntity getUserProfileByJwt(@RequestHeader("Authorization") String jwt) {
        return userService.findUserProfileByJwt(jwt);
    }

    @GetMapping("/email/{email}")
    public UserEntity getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

}
