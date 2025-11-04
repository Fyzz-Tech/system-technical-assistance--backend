package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.sale.SaleRequestDTO;
import com.technical_assistance.project.dtos.sale.SaleResponseDTO;
import com.technical_assistance.project.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    Sale toEntity(SaleRequestDTO saleRequestDTO);
    SaleResponseDTO toResponseDTO(Sale sale);

}
