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
public class SaleResponseDTO {

    private LocalDate dateHour;
    private StatusSale status;
    private Double totalValue;
    private List<ProductItem> items = new ArrayList<>();

    public SaleResponseDTO(Sale sale) {
        this.dateHour = sale.getDateHour();
        this.status = sale.getStatus();
        this.totalValue = sale.getTotalValue();
        this.items = sale.getItems() != null ? new ArrayList<>(sale.getItems()) : new ArrayList<>();
    }
}
