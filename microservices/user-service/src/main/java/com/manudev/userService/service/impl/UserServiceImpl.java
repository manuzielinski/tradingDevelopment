package com.manudev.userService.service.impl;

import com.manudev.userService.config.filter.JwtUtil;
import com.manudev.userService.mapper.UserMapper;
import com.manudev.common.dto.UserDTO;
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
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> listAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                .map(userMapper::userToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByID(Long userID) {
        UserEntity userEntity = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToDTO(userEntity);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.dtoToUser(userDTO);
        userRepository.save(userEntity);
        return userMapper.userToDTO(userEntity);
    }

    @Override
    public UserDTO updateUser(Long userID, UserDTO userDTO) {
        // first, we're going to find the old user:
        UserEntity oldUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // set the id
        UserEntity updatedEntity = userMapper.dtoToUser(userDTO);
        updatedEntity.setUserID(oldUser.getUserID());

        // save the updated entity
        UserEntity savedEntity = userRepository.save(updatedEntity);
        return userMapper.userToDTO(savedEntity);
    }

    @Override
    public void deleteUser(Long userID) {
        UserEntity userEntity = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userEntity.setActive(false);
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO findUserProfileByJwt(String jwt) {
        String email = jwtUtil.getEmailFromToken(jwt);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email no encontrado"));
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
