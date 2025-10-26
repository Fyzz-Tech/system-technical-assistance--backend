package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.client.ClientRequestDTO;
import com.technical_assistance.project.dtos.client.ClientResponseDTO;
import com.technical_assistance.project.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDTO clientRequestDTO);
    ClientResponseDTO toResponseDTO(Client client);
    void updateClientFromDTO(ClientRequestDTO dto, @MappingTarget Client entity);

}
