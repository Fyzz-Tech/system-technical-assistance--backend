package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.service.ServiceRequestDTO;
import com.technical_assistance.project.dtos.service.ServiceResponseDTO;
import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.mapper.ServiceMapper;
import com.technical_assistance.project.services.ServiceAssistenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceController {

    private final ServiceAssistenceService service;
    private final ServiceMapper mapper;

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> findAll(){
        List<ServiceResponseDTO> services =  service.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDTO> findById(@PathVariable String serviceId){
        ServiceAssistence serviceFind = service.findById(serviceId);
        ServiceResponseDTO response = mapper.toResponseDTO(serviceFind);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> create(@RequestBody @Valid ServiceRequestDTO dto){
        ServiceAssistence newService = service.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newService.getId())
                .toUri();
        System.out.println(dto);
        ServiceResponseDTO response = mapper.toResponseDTO(newService);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDTO> update(@RequestBody @Valid ServiceRequestDTO dto, @PathVariable String serviceId){
        ServiceAssistence current = service.update(dto, serviceId);
        ServiceResponseDTO response = mapper.toResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> delete(@PathVariable String serviceId){
        service.delete(serviceId);
        return ResponseEntity.noContent().build();
    }
}
