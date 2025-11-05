package com.technical_assistance.project.dtos.product;

import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestUpdateDTO {

    @NotNull private String name;
    @NotNull private Status status;
    @NotNull private Category category;
    @NotNull private Double priceForSale;
    @NotNull private Double priceAtCost;
    @NotNull private Integer quantity;

    public void updateEntity(Product product) {
        product.setName(this.name);
        product.setStatus(this.status);
        product.setCategory(this.category);
        product.setPriceForSale(this.priceForSale);
        product.setPriceAtCost(this.priceAtCost);
    }
}
