package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductResponseDTO;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.exceptions.ResourceNotFoundException;
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
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + id + " não existe."));
    }

    @Transactional
    public Product create(ProductRequestDTO dto){
        Product newProduct = mapper.toEntity(dto);
        return repository.save(newProduct);
    }

    @Transactional
    public Product update(String id, ProductRequestDTO dto) {
        try {
            Product current = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + id + " não existe."));
            mapper.updateProductFromDTO(dto, current);
            return repository.save(current);
        } catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(String id){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + id + " não existe."));
        repository.delete(product);
    }
}
