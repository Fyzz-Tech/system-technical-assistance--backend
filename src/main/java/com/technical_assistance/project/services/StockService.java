package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.product.ProductRequestDTO;
import com.technical_assistance.project.dtos.product.ProductRequestUpdateDTO;
import com.technical_assistance.project.dtos.product.ProductStockDTO;
import com.technical_assistance.project.dtos.stock.StockExitMovementDTO;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.entities.Stock;
import com.technical_assistance.project.entities.StockMovement;
import com.technical_assistance.project.enuns.MovementType;
import com.technical_assistance.project.exceptions.ResourceNotFoundException;
import com.technical_assistance.project.repositories.ProductRepository;
import com.technical_assistance.project.repositories.StockMovementRepository;
import com.technical_assistance.project.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockMovementRepository stockMovementRepository;

    @Transactional
    public ProductStockDTO createProductWithStock(ProductRequestDTO dto, Integer quantity) {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("A quantidade inicial do produto deve ser >= 0.");
        }

        Product product = dto.toEntity();
        Product savedProduct = productRepository.save(product);

        Stock stock = new Stock();
        stock.setProduct(savedProduct);
        stock.setQuantityCurrent(quantity);
        stockRepository.save(stock);

        if (quantity > 0) {
            StockMovement movement = new StockMovement();
            movement.setProduct(savedProduct);
            movement.setQuantity(quantity);
            movement.setType(MovementType.ENTRADA);
            movement.setDateHour(LocalDateTime.now());
            stockMovementRepository.save(movement);
        }

        return new ProductStockDTO(savedProduct, stock);
    }


    @Transactional
    public Product updateProduct(String id, ProductRequestUpdateDTO dto) {
        Product current = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + id + " não existe."));

        dto.updateEntity(current);
        Product updatedProduct = productRepository.save(current);

        if (dto.getQuantity() != null) {
            Stock stock = stockRepository.findFirstByProductId(id)
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado para este produto"));
            stock.setQuantityCurrent(dto.getQuantity());
            stockRepository.save(stock);
        }
        return updatedProduct;
    }

    @Transactional
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + productId + " não existe."));

        stockRepository.deleteByProductId(productId);
        stockMovementRepository.deleteByProductId(productId);
        productRepository.delete(product);
    }

    @Transactional
    public void registerExit(StockExitMovementDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + dto.getProductId() + " não existe."));

        Stock stock = stockRepository.findFirstByProductId(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        if (stock.getQuantityCurrent() < dto.getQuantity()) {
            throw new RuntimeException("Estoque com quantidade insuficiente.");
        }

        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setQuantity(dto.getQuantity());
        movement.setType(MovementType.SAIDA);
        movement.setOrigin(dto.getOrigin());
        movement.setDateHour(LocalDateTime.now());
        stockMovementRepository.save(movement);

        stock.setQuantityCurrent(stock.getQuantityCurrent() - dto.getQuantity());
        stockRepository.save(stock);
    }

    public List<ProductStockDTO> listAllWithStock() {
        return productRepository.findAll().stream()
                .map(p -> new ProductStockDTO(p, stockRepository.findFirstByProductId(p.getId()).orElse(null)))
                .toList();
    }
}
