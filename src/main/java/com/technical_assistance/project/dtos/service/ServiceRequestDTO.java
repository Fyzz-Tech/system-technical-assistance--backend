package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.enuns.CategoryService;
import com.technical_assistance.project.enuns.Status;
import jakarta.validation.constraints.NotNull;

public record ServiceRequestDTO(
        @NotNull String name,
        @NotNull CategoryService category,
        @NotNull String description,
        @NotNull Double price,
        @NotNull Status status,
        @NotNull String clientId) {
}
