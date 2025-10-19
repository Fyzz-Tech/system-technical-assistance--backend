package com.technical_assistance.project.dtos.user;

import com.technical_assistance.project.enuns.RoleUser;

public record UserResponseDTO(String name, String email, RoleUser role) {}
