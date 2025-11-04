package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.enuns.StatusSale;

import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(LocalDateTime dateHour, StatusSale status, Double totalValue, List<ProductItem> items) {

    public SaleResponseDTO(com.technical_assistance.project.entities.Sale sale) {
        this(
                sale.getDateHour(),
                sale.getStatus(),
                sale.getTotalValue(),
                sale.getItems()
        );
    }
}
