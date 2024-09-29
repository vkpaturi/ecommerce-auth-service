package com.project.spring.authservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.spring.authservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    //    private Role role;
    private String authority;

    public CustomGrantedAuthority() {}

    public CustomGrantedAuthority(Role role) {
//        this.role = role;
        this.authority = role.getRoleName();
    }

    @Override
    public String getAuthority() {
//        return role.getName();
        return authority;
    }
}
