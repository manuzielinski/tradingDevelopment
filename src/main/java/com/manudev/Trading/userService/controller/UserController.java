package com.manudev.Trading.userService.controller;

import com.manudev.Trading.userService.dto.UserDTO;
import com.manudev.Trading.userService.repository.UserRepository;
import com.manudev.Trading.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public UserDTO getUserByID(@PathVariable Long userID) {
        return userService.getUserByID(userID);
    }

    @GetMapping
    public List<UserDTO> listAllUsers() {
        return userService.listAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userID}")
    public UserDTO updateUser(@PathVariable Long userID, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userID, userDTO);
    }

    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable Long userID) {
        userService.deleteUser(userID);
        return "Se ha eliminado el usuario" + userID;
    }
}
