package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.client.ClientRequestDTO;
import com.technical_assistance.project.dtos.client.ClientResponseDTO;
import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll(){
        List<ClientResponseDTO> clients = service.findAll();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody @Valid ClientRequestDTO dto){
        Client newClient = service.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClient.getId())
                .toUri();
        ClientResponseDTO response = new ClientResponseDTO(newClient);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable String id, @RequestBody @Valid ClientRequestDTO dto){
        Client current = service.update(id, dto);
        ClientResponseDTO response = new ClientResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
