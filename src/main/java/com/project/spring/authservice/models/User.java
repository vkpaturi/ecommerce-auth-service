package com.project.spring.authservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{

    private String email;
    @JsonIgnore
    private String password;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private boolean emailVerified;
}
