package com.technical_assistance.project.dtos.client;

import com.technical_assistance.project.entities.ServiceAssistence;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClientRequestDTO(@NotNull String name, @NotNull String telephone, @NotNull String email, List<ServiceAssistence> services) {
}
