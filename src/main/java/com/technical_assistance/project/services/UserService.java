package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.user.UserRequestDTO;
import com.technical_assistance.project.entities.User;
import com.technical_assistance.project.mapper.UserMapper;
import com.technical_assistance.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public User create(UserRequestDTO dto) {
        User user =  mapper.toEntity(dto);
        return repository.save(user);
    }

    @Transactional
    public User update(String id, UserRequestDTO dto) {
        try {
            User current = repository.findById(id).orElse(null);
            mapper.updateUserFromDTO(dto, current);
            return repository.save(current);
        } catch(Exception e) {
            throw e;
        }
    }

    public void delete(String id) {
        User user = repository.findById(id).orElse(null);
        repository.delete(user);
    }
}
