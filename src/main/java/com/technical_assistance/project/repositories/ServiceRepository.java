package com.technical_assistance.project.repositories;

import com.technical_assistance.project.entities.ServiceAssistence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository  extends MongoRepository<ServiceAssistence, String> {
}
