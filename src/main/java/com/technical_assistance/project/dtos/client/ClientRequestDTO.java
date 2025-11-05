package com.technical_assistance.project.dtos.client;

import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.entities.ServiceAssistence;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    @NotNull private String name;
    @NotNull private String telephone;
    @NotNull private String email;
    private List<ServiceAssistence> services = new ArrayList<>();

    public Client toEntity() {
        Client client = new Client();
        client.setName(this.name);
        client.setTelephone(this.telephone);
        client.setEmail(this.email);
        client.setServices(this.services != null ? this.services : new ArrayList<>());
        return client;
    }

    public void updateEntity(Client client) {
        client.setName(this.name);
        client.setTelephone(this.telephone);
        client.setEmail(this.email);
        client.setServices(this.services != null ? this.services : new ArrayList<>());
    }
}
