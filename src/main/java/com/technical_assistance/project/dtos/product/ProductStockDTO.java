package com.technical_assistance.project.dtos.product;

import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.entities.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDTO {

    private String productId;
    private String productName;
    private Category category;
    private Integer quantityCurrent;
    private Double priceForSale;
    private Double priceAtCost;
    private Status status;

    public ProductStockDTO(Product product, Stock stock) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.category = product.getCategory();
        this.quantityCurrent = stock != null ? stock.getQuantityCurrent() : 0;
        this.priceForSale = product.getPriceForSale();
        this.priceAtCost = product.getPriceAtCost();
        this.status = product.getStatus();
    }

    public ProductStockDTO(Product product) {
        this(product, null);
    }
}
