package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDTO {

    private String name;
    private String description;
    private Double price;
    private Status status;

    public ServiceResponseDTO(ServiceAssistence service) {
        this.name = service.getName();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.status = service.getStatus();
    }
}
