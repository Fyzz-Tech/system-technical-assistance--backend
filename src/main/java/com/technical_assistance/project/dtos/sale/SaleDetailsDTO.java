package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.entities.Sale;

import java.time.LocalDate;
import java.util.List;

public record SaleDetailsDTO(String client, List<String> items, Double totalValue, String saleId, LocalDate createdAt) {
    public SaleDetailsDTO(Sale sale){
        this(
                sale.getClient().getName(),
                sale.getItems().stream()
                        .map(ProductItem::getProductName)
                        .toList(),
                sale.getTotalValue(),
                sale.getId(),
                sale.getDateHour().toLocalDate()
        );
    }
}
