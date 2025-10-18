package com.technical_assistance.project.dtos.product;

import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;

public record ProductResponseDTO(String name, Category category, Integer quantityStock, Double unitPrice, Status status) {

    public ProductResponseDTO(Product product) {
        this(
                product.getName(),
                product.getCategory(),
                product.getQuantityStock(),
                product.getUnitPrice(),
                product.getStatus());
    }
}
