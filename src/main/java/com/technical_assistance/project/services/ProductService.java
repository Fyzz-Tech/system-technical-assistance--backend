package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductResponseDTO;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.mapper.ProductMapper;
import com.technical_assistance.project.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    @Transactional
    public Product findById(String id){
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Product create(ProductRequestDTO dto){
        Product newProduct = mapper.toEntity(dto);
        return repository.save(newProduct);
    }

    @Transactional
    public Product update(String id, ProductRequestDTO dto) {
        try {
            Product current = repository.findById(id).orElse(null);
            mapper.updateProductFromDTO(dto, current);
            return repository.save(current);
        } catch(Exception e) {
            throw e;
        }
    }

    @Transactional
    public void delete(String id){
        Product product = repository.findById(id).orElse(null);
        repository.delete(product);
    }
}
