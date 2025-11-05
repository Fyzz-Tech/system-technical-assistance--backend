package com.technical_assistance.project.dtos.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemRequestDTO {

    @NotNull private String productId;
    @NotNull private Integer quantity;

}
