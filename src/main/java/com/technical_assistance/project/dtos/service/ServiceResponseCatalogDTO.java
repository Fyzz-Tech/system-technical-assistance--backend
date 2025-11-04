package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.entities.ServiceAssistence;

import java.time.LocalDate;

public record ServiceResponseCatalogDTO(
        String clientId,
        String name,
        Double price,
        LocalDate date) {

    public ServiceResponseCatalogDTO(ServiceAssistence service) {
        this(
                service.getClient().getName(),
                service.getName(),
                service.getPrice(),
                service.getDate()
        );
    }
}
