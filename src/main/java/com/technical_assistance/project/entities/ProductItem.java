package com.technical_assistance.project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {

    private String productId;
    private Integer quantity;
    private String productName;
    private Double productPrice;

    public ProductItem(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Double getTotalValueItemProduct(){
        return quantity * productPrice;
    }
}
