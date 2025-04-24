package com.manudev.Trading.userService.service;

import com.manudev.Trading.userService.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public List<UserDTO> listAllUsers();
    UserDTO getUserByID(Long userID);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long userID, UserDTO userDTO);
    void deleteUser(Long userID);
    UserDTO findUserProfileByJwt(String jwt);
    UserDTO findUserByEmail(String email);
}
