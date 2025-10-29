package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.service.ServiceRequestDTO;
import com.technical_assistance.project.dtos.service.ServiceResponseDTO;
import com.technical_assistance.project.entities.ServiceAssistence;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceAssistence toEntity(ServiceRequestDTO serviceRequestDTO);
    ServiceResponseDTO toResponseDTO(ServiceAssistence service);
    void updateServiceFromDTO(ServiceRequestDTO dto, @MappingTarget ServiceAssistence entity);
}
