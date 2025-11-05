package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductRequestUpdateDTO;
import com.technical_assistance.project.dtos.product.ProductStockDTO;
import com.technical_assistance.project.dtos.stock.StockExitMovementDTO;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.services.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockService service;

    @PostMapping("/product")
    public ResponseEntity<ProductStockDTO> createProduct(@RequestBody @Valid ProductRequestDTO dto, @RequestParam Integer quantity) {
        ProductStockDTO response = service.createProductWithStock(dto, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductStockDTO> updateProduct(@RequestBody @Valid ProductRequestUpdateDTO dto, @PathVariable String id) {
        Product current = service.updateProduct(id, dto);
        ProductStockDTO response = new ProductStockDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/product/exit")
    public ResponseEntity<Void> registerExit(@RequestBody StockExitMovementDTO dto) {
        service.registerExit(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductStockDTO>> listAllWithStock() {
        List<ProductStockDTO> list = service.listAllWithStock();
        return ResponseEntity.ok(list);
    }
}
