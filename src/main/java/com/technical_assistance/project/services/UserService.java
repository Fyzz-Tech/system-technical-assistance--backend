package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.user.UserRequestDTO;
import com.technical_assistance.project.entities.User;
import com.technical_assistance.project.exceptions.ResourceNotFoundException;
import com.technical_assistance.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User create(UserRequestDTO dto) {
        User user =  dto.toEntity();
        return repository.save(user);
    }

    @Transactional
    public User update(String id, UserRequestDTO dto) {
        try {
            User current = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com ID: " + id + " não existe."));
            dto.updateEntity(current);
            return repository.save(current);
        } catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(String id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com ID: " + id + " não existe."));
        repository.delete(user);
    }
}
