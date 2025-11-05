package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.client.ClientRequestDTO;
import com.technical_assistance.project.dtos.client.ClientResponseDTO;
import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.exceptions.ResourceNotFoundException;
import com.technical_assistance.project.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public List<ClientResponseDTO> findAll(){
        return repository.findAll().stream().map(ClientResponseDTO::new).toList();
    }

    @Transactional
    public Client create(ClientRequestDTO dto){
        Client newClient = dto.toEntity();
        return repository.save(newClient);
    }

    @Transactional
    public Client update(String id, ClientRequestDTO dto){
        try {
            Client current = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com ID: " + id + " não existe."));
            dto.updateEntity(current);
            return repository.save(current);
        }catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(String id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com ID: " + id + " não existe."));
        repository.delete(client);
    }
}
