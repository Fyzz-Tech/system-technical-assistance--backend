package com.technical_assistance.project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    private String id;
    private String name;
    private String telephone;
    private String email;
    private List<ServiceAssistence> services;
}
