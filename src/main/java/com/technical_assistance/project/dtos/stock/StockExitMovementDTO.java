package com.technical_assistance.project.dtos.stock;

import com.technical_assistance.project.enuns.OriginMovement;

public record StockExitMovementDTO(String productId, Integer quantity, OriginMovement origin) {
}
