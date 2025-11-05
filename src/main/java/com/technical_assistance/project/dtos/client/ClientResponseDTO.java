package com.technical_assistance.project.dtos.client;

import com.technical_assistance.project.dtos.service.ServiceResponseDTO;
import com.technical_assistance.project.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private String name;
    private String telephone;
    private String email;
    private List<ServiceResponseDTO> services = new ArrayList<>();

    public ClientResponseDTO(Client client) {
        this.name = client.getName();
        this.telephone = client.getTelephone();
        this.email = client.getEmail();
        this.services = client.getServices() != null
                ? client.getServices().stream().map(ServiceResponseDTO::new).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
