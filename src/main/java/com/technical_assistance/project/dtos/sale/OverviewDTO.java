package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.entities.Sale;
import com.technical_assistance.project.enuns.StatusSale;

import java.time.LocalDate;
import java.util.List;

public record OverviewDTO(String client, List<ProductItem> items, LocalDate createdAt, Double totalValue, StatusSale status) {
    public OverviewDTO(Sale sale){
        this(
                sale.getClient().getName(),
                sale.getItems(),
                sale.getDateHour().toLocalDate(),
                sale.getTotalValue(),
                sale.getStatus()
        );
    }
}
