package com.manudev.Trading.userService.service.impl;

import com.manudev.Trading.userService.dto.UserDTO;
import com.manudev.Trading.userService.mapper.UserMapper;
import com.manudev.Trading.userService.model.UserEntity;
import com.manudev.Trading.userService.repository.UserRepository;
import com.manudev.Trading.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> listAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByID(Long userID) {
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return userMapper.userToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = userMapper.dtoToUser(userDTO);
        userRepository.save(user);
        return userMapper.userToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userID, UserDTO userDTO) {
        // first, we're going to find the old user:
        UserEntity oldUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // later, locate the new one
        UserEntity updatedUser = userMapper.dtoToUser(userDTO);
        updatedUser.setUserID(oldUser.getUserID());
        return userMapper.userToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userID) {
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDTO findUserProfileByJwt(String jwt) {
        String email = JwtProvi
        return null;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return null;
    }
}
