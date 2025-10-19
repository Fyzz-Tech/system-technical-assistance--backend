package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductResponseDTO;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.mapper.ProductMapper;
import com.technical_assistance.project.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable String id) {
        Product product = service.findById(id);
        ProductResponseDTO response = mapper.toResponseDTO(product);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO dto) {
        Product newProduct = service.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        ProductResponseDTO response = mapper.toResponseDTO(newProduct);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable String id, @RequestBody @Valid ProductRequestDTO dto) {
        Product current = service.update(id, dto);
        ProductResponseDTO response = mapper.toResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
