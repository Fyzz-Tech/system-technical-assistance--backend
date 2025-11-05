package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.entities.Sale;
import com.technical_assistance.project.enuns.StatusSale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OverviewDTO {

    private String client;
    private List<ProductItem> items = new ArrayList<>();
    private LocalDate createdAt;
    private Double totalValue;
    private StatusSale status;

    public OverviewDTO(Sale sale) {
        this.client = sale.getClient().getName();
        this.items = sale.getItems() != null ? sale.getItems() : new ArrayList<>();
        this.createdAt = sale.getDateHour() != null ? sale.getDateHour() : null;
        this.totalValue = sale.getTotalValue();
        this.status = sale.getStatus();
    }
}
