package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.stock.StockResponseDTO;
import com.technical_assistance.project.entities.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockResponseDTO toResponseDTO(Stock stock);
}
