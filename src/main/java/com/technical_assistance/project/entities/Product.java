package com.technical_assistance.project.entities;

import com.technical_assistance.project.enuns.Category;
import com.technical_assistance.project.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;
    private Status status;
    private Category category;
    private Double unitPrice;
    private Integer quantityStock;
}
