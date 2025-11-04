package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.enuns.Status;

public record ServiceResponseDTO(String name, String description, Double price, Status status) {

    public ServiceResponseDTO(ServiceAssistence service){
        this(
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getStatus());
    }
}
