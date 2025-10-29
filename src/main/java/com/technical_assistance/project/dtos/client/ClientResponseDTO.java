package com.technical_assistance.project.dtos.client;

import com.technical_assistance.project.dtos.service.ServiceResponseDTO;
import com.technical_assistance.project.entities.Client;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ClientResponseDTO(String name, String telephone, String email, List<ServiceResponseDTO> services) {

    public ClientResponseDTO(Client client){
        this(
                client.getName(),
                client.getTelephone(),
                client.getEmail(),
                client.getServices() != null ? client.getServices().stream().map(ServiceResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList());
    }
}
