package com.technical_assistance.project.entities;

import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private Status status;
    private Category category;
    private Double priceForSale;
    private Double priceAtCost;

    public Product(String name, Status status, Category category, Double priceForSale, Double priceAtCost) {
        this.name = name;
        this.status = status;
        this.category = category;
        this.priceForSale = priceForSale;
        this.priceAtCost = priceAtCost;
    }

}
