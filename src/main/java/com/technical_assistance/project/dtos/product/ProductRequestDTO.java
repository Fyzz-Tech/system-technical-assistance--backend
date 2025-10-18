package com.technical_assistance.project.dtos.product;

import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotNull String name,
        @NotNull Status status,
        @NotNull Category category,
        @NotNull Double unitPrice,
        @NotNull Integer quantityStock) {
}
