package com.manudev.userService.service.impl;

import com.manudev.userService.config.filter.JwtUtil;
import com.manudev.userService.mapper.UserMapper;
import com.manudev.userService.model.UserEntity;
import com.manudev.userService.repository.UserRepository;
import com.manudev.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserByID(Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(Long userID, UserEntity userEntity) {
        // first, we're going to find the old user:
        UserEntity oldUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // later, locate the new one
        userEntity.setUserID(oldUser.getUserID());
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long userID) {
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserEntity findUserProfileByJwt(String jwt) {
        String email = jwtUtil.getEmailFromToken(jwt);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
