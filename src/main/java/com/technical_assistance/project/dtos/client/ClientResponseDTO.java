package com.technical_assistance.project.dtos.client;

import com.technical_assistance.project.entities.Client;

public record ClientResponseDTO(String name, String telephone, String email) {

    public ClientResponseDTO(Client client){
        this(client.getName(), client.getTelephone(), client.getEmail());
    }
}
