package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.dtos.client.ClientResponseSimpleDTO;
import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.enuns.StatusService;

public record ServiceResponseDTO(String type, String description, Double price, StatusService status, ClientResponseSimpleDTO client) {

    public ServiceResponseDTO(ServiceAssistence service){
        this(
                service.getType(),
                service.getDescription(),
                service.getPrice(),
                service.getStatus(),
                new ClientResponseSimpleDTO(service.getClient().getName()));
    }

}
