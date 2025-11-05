package com.technical_assistance.project.dtos.authentication;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationDTO {

    @NotNull private String email;
    @NotNull private String password;
}
