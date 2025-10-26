package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.client.ClientRequestDTO;
import com.technical_assistance.project.dtos.client.ClientResponseDTO;
import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.mapper.ClientMapper;
import com.technical_assistance.project.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    public List<ClientResponseDTO> findAll(){
        return repository.findAll().stream().map(ClientResponseDTO::new).toList();
    }

    @Transactional
    public Client create(ClientRequestDTO dto){
        Client newClient = mapper.toEntity(dto);
        return repository.save(newClient);
    }

    @Transactional
    public Client update(String id, ClientRequestDTO dto){
        try {
            Client current = repository.findById(id).orElse(null);
            mapper.updateClientFromDTO(dto, current);;
            return repository.save(current);
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional
    public void delete(String id){
        Client client = repository.findById(id).orElse(null);
        repository.delete(client);
    }
}
