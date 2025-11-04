package com.technical_assistance.project.repositories;

import com.technical_assistance.project.entities.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock,String> {
    Optional<Stock> findFirstByProductId(String productId);
    void deleteByProductId(String productId);
}
