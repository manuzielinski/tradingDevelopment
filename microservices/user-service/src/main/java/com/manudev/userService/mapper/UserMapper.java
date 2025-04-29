package com.manudev.userService.mapper;

import com.manudev.common.dto.UserDTO;
import com.manudev.userService.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "entityField", target = "dtoField")
    UserDTO userToDTO(UserEntity user);
    @Mapping(source = "dtoField", target = "entityField")
    UserEntity dtoToUser(UserDTO userDTO);
}
