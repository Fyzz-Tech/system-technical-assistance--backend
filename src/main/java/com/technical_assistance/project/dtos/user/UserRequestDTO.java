package com.technical_assistance.project.dtos.user;

import com.technical_assistance.project.entities.User;
import com.technical_assistance.project.enuns.RoleUser;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull private String name;
    @NotNull private String email;
    @NotNull private String password;
    @NotNull private RoleUser role;

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRole(this.role);
        return user;
    }

    public void updateEntity(User user) {
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRole(this.role);
    }
}
