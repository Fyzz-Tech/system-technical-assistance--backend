package com.technical_assistance.project.dtos.product;

import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.entities.Stock;

public record ProductStockDTO(
        String productId,
        String productName,
        Category category,
        Integer quantityCurrent,
        Double priceForSale,
        Double priceAtCost,
        Status status
) {
    public ProductStockDTO(Product product, Stock stock) {
        this(
                product.getId(),
                product.getName(),
                product.getCategory(),
                stock != null ? stock.getQuantityCurrent() : 0,
                product.getPriceForSale(),
                product.getPriceAtCost(),
                product.getStatus()
        );
    }
}
