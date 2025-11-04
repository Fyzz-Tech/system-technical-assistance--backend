package com.technical_assistance.project.entities;

import com.technical_assistance.project.enuns.CategoryService;
import com.technical_assistance.project.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAssistence {

    @Id
    private String id;
    private String name;
    private CategoryService category;
    private String description;
    private Double price;
    private Status status;
    private Client client;
    private LocalDate date;
}
