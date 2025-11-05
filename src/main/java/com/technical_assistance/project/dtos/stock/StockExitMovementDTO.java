package com.technical_assistance.project.dtos.stock;

import com.technical_assistance.project.enuns.OriginMovement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockExitMovementDTO {

    private String productId;
    private Integer quantity;
    private OriginMovement origin;

}
