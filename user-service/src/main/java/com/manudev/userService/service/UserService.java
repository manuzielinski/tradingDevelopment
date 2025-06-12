package com.manudev.userService.service;

import com.manudev.common.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO getUserByID(Long userID);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long userID, UserDTO userDTO);
    void deleteUser(Long userID);
    UserDTO findUserProfileByJwt(String jwt);
    UserDTO findUserByEmail(String email);
}
