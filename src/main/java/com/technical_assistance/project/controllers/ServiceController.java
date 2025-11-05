package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.service.ServiceRequestDTO;
import com.technical_assistance.project.dtos.service.ServiceResponseCatalogDTO;
import com.technical_assistance.project.dtos.service.ServiceResponseDTO;
import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.enuns.CategoryService;
import com.technical_assistance.project.services.ServiceAssistenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceController {

    private final ServiceAssistenceService service;

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> findAll(){
        List<ServiceResponseDTO> services =  service.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDTO> findById(@PathVariable String serviceId){
        ServiceAssistence serviceFind = service.findById(serviceId);
        ServiceResponseDTO response = new ServiceResponseDTO(serviceFind);
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
        ServiceResponseDTO response = new ServiceResponseDTO(newService);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDTO> update(@RequestBody @Valid ServiceRequestDTO dto, @PathVariable String serviceId){
        ServiceAssistence current = service.update(dto, serviceId);
        ServiceResponseDTO response = new ServiceResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> delete(@PathVariable String serviceId){
        service.delete(serviceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalog")
    public Map<CategoryService, List<ServiceResponseCatalogDTO>> getCatalog() {
        return service.getCatalogByCategory();
    }
}
