package com.technical_assistance.project.repositories;

import com.technical_assistance.project.entities.StockMovement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockMovementRepository extends MongoRepository<StockMovement,String> {
    void deleteByProductId(String productId);
}
