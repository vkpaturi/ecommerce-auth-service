package com.project.spring.authservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{

    private String email;
    private String password;
    private String name;
    @ManyToMany
    private List<Role> roles;
    private boolean emailVerified;
}
