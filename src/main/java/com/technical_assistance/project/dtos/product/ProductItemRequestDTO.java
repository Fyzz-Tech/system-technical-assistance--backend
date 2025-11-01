package com.technical_assistance.project.dtos.product;

import jakarta.validation.constraints.NotNull;

public record ProductItemRequestDTO(@NotNull String productId, @NotNull Integer quantity) {
}
