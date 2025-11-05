package com.technical_assistance.project.dtos.service;

import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.enuns.CategoryService;
import com.technical_assistance.project.enuns.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDTO {

    @NotNull private String name;
    @NotNull private CategoryService category;
    @NotNull private String description;
    @NotNull private Double price;
    @NotNull private Status status;
    @NotNull private String clientId;

    public ServiceAssistence toEntity() {
        ServiceAssistence service = new ServiceAssistence();
        service.setName(this.name);
        service.setCategory(this.category);
        service.setDescription(this.description);
        service.setPrice(this.price);
        service.setStatus(this.status);
        return service;
    }

    public void updateEntity(ServiceAssistence service) {
        service.setName(this.name);
        service.setCategory(this.category);
        service.setDescription(this.description);
        service.setPrice(this.price);
        service.setStatus(this.status);
    }
}

