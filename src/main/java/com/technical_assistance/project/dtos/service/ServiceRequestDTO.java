package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.enuns.StatusService;
import jakarta.validation.constraints.NotNull;

public record ServiceRequestDTO(
        @NotNull String type,
        @NotNull String description,
        @NotNull Double price,
        @NotNull StatusService status,
        @NotNull String clientId) {
}
