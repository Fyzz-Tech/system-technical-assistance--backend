package com.technical_assistance.project.entities;

import com.technical_assistance.project.enuns.StatusService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAssistence {

    @Id
    private String id;
    private String type;
    private String description;
    private Double price;
    private StatusService status;
    private Client client;
}
