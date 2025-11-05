package com.technical_assistance.project.dtos.user;

import com.technical_assistance.project.entities.User;
import com.technical_assistance.project.enuns.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;
    private RoleUser role;

    public UserResponseDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole(); // já é RoleUser
    }

}
