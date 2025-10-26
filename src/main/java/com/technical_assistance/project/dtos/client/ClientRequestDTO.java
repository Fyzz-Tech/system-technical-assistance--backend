package com.technical_assistance.project.dtos.client;

import jakarta.validation.constraints.NotNull;

public record ClientRequestDTO(@NotNull String name, @NotNull String telephone, @NotNull String email) {
}
