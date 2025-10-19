package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.user.UserRequestDTO;
import com.technical_assistance.project.dtos.user.UserResponseDTO;
import com.technical_assistance.project.entities.User;
import com.technical_assistance.project.mapper.UserMapper;
import com.technical_assistance.project.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto) {
        User newUser = service.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        UserResponseDTO response = mapper.toResponseDTO(newUser);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO dto, @PathVariable String id) {
        User current = service.update(id, dto);
        UserResponseDTO response = mapper.toResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
