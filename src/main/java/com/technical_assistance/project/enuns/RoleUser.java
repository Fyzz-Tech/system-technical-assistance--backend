package com.technical_assistance.project.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleUser {

    ADMIN("admin"),
    OPERADOR("operador");

    private String role;
}
