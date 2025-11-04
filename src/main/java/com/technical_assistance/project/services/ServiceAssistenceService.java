package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.service.ServiceRequestDTO;
import com.technical_assistance.project.dtos.service.ServiceResponseCatalogDTO;
import com.technical_assistance.project.dtos.service.ServiceResponseDTO;
import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.entities.ServiceAssistence;
import com.technical_assistance.project.enuns.CategoryService;
import com.technical_assistance.project.exceptions.ResourceNotFoundException;
import com.technical_assistance.project.mapper.ServiceMapper;
import com.technical_assistance.project.repositories.ClientRepository;
import com.technical_assistance.project.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceAssistenceService {

    private final ServiceRepository repository;
    private final ClientRepository clientRepository;
    private final ServiceMapper mapper;

    public List<ServiceResponseDTO> findAll(){
        return repository.findAll().stream().map(ServiceResponseDTO::new).toList();
    }

    public ServiceAssistence findById(String serviceId){
        return repository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Serviço com ID: " + serviceId + " não existe."));
    }

    @Transactional
    public ServiceAssistence create(ServiceRequestDTO dto){
        Client client = clientRepository.findById(dto.clientId()).orElseThrow(() -> new ResourceNotFoundException("Client com ID: " + dto.clientId() + " não existe."));
        ServiceAssistence newService = mapper.toEntity(dto);
        newService.setDate(LocalDate.now());
        newService.setClient(client);
        newService.setCategory(dto.category());
        return repository.save(newService);
    }

    @Transactional
    public ServiceAssistence update(ServiceRequestDTO dto, String serviceId){
        try {
            Client client = clientRepository.findById(dto.clientId()).orElseThrow(() -> new ResourceNotFoundException("Client com ID: " + dto.clientId() + " não existe."));
            ServiceAssistence current = repository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Serviço com ID: " + serviceId + " não existe."));
            current.setClient(client);
            mapper.updateServiceFromDTO(dto, current);
            return repository.save(current);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(serviceId);
        }
    }

    @Transactional
    public void delete(String serviceId){
        ServiceAssistence service = repository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Serviço com ID: " + serviceId + " não existe."));
        repository.delete(service);
    }

    public Map<CategoryService, List<ServiceResponseCatalogDTO>> getCatalogByCategory() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        ServiceAssistence::getCategory,
                        Collectors.mapping(ServiceResponseCatalogDTO::new, Collectors.toList())
                ));
    }

}
