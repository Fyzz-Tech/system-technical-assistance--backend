package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.entities.ServiceAssistence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseCatalogDTO {

    private String clientId;
    private String name;
    private Double price;
    private LocalDate date;

    public ServiceResponseCatalogDTO(ServiceAssistence service) {
        this.clientId = service.getClient().getName();
        this.name = service.getName();
        this.price = service.getPrice();
        this.date = service.getDate();
    }
}
