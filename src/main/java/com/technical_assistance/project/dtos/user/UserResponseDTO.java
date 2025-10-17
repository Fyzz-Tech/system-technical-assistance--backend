package com.technical_assistance.project.dtos.user;

import com.technical_assistance.project.entities.User;
import com.technical_assistance.project.enuns.RoleUser;

public record UserResponseDTO(String name, String email, RoleUser role) {

    public UserResponseDTO (User user) {
        this (
                user.getName(),
                user.getEmail(),
                user.getRole());
    }
}
