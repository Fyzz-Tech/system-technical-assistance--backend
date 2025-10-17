package com.technical_assistance.project.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull String name,
        @NotNull String email,
        @NotNull String password,
        @NotNull String role) {
}
