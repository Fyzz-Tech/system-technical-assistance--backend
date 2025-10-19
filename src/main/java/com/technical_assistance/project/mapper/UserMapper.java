package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.user.UserRequestDTO;
import com.technical_assistance.project.dtos.user.UserResponseDTO;
import com.technical_assistance.project.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO toResponseDTO(User user);
    void updateUserFromDTO(UserRequestDTO dto, @MappingTarget User entity);
}
