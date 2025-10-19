package com.technical_assistance.project.mapper;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductResponseDTO;
import com.technical_assistance.project.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);
    ProductResponseDTO toResponseDTO(Product product);
    void updateProductFromDTO(ProductRequestDTO dto, @MappingTarget Product entity);
}
