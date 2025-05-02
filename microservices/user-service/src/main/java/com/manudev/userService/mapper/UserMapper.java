package com.manudev.userService.mapper;

import com.manudev.common.dto.UserDTO;
import com.manudev.userService.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToDTO(UserEntity user);

    UserEntity dtoToUser(UserDTO userDTO);
}
