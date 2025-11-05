package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailsDTO {

    private String client;
    private List<String> items = new ArrayList<>();
    private Double totalValue;
    private String saleId;
    private LocalDate createdAt;

    public SaleDetailsDTO(Sale sale) {
        this.client = sale.getClient().getName();
        this.items = sale.getItems() != null
                ? sale.getItems().stream()
                .map(ProductItem::getProductName)
                .collect(Collectors.toList())
                : new ArrayList<>();
        this.totalValue = sale.getTotalValue();
        this.saleId = sale.getId();
        this.createdAt = sale.getDateHour() != null ? sale.getDateHour() : null;
    }
}
