package com.technical_assistance.project.entities;

import com.technical_assistance.project.enuns.MovementType;
import com.technical_assistance.project.enuns.OriginMovement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "stockMovement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {

    @Id
    private String id;
    private Product product;
    private LocalDateTime dateHour;
    private Integer quantity;
    private MovementType type;
    private OriginMovement origin;
}
