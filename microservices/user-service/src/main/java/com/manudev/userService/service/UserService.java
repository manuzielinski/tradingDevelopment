package com.manudev.userService.service;

import com.manudev.userService.model.UserEntity;

import java.util.List;

public interface UserService {

    public List<UserEntity> listAllUsers();
    UserEntity getUserByID(Long userID);
    UserEntity createUser(UserEntity userEntity);
    UserEntity updateUser(Long userID, UserEntity userEntity);
    void deleteUser(Long userID);
    UserEntity findUserProfileByJwt(String jwt);
    UserEntity findUserByEmail(String email);
}
