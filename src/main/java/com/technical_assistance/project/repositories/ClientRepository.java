package com.technical_assistance.project.repositories;

import com.technical_assistance.project.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findByEmail(String email);
}
