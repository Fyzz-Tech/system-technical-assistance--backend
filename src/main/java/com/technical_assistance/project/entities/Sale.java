package com.technical_assistance.project.entities;

import com.technical_assistance.project.enuns.PaymentMethod;
import com.technical_assistance.project.enuns.StatusSale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    private String id;
    private LocalDateTime dateHour;
    private Double totalValue;
    private PaymentMethod paymentMethod;
    private StatusSale status;
    private List<ProductItem> items;
    private Client client;
}
