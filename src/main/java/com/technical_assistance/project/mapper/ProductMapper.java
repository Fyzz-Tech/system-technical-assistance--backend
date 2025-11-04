package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductRequestUpdateDTO;
import com.technical_assistance.project.dtos.product.ProductStockDTO;
import com.technical_assistance.project.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);
    ProductStockDTO toResponseDTO(Product product);
    void updateProductFromDTO(ProductRequestUpdateDTO dto, @MappingTarget Product entity);
}
