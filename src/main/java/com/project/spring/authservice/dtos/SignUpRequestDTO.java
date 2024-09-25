package com.project.spring.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {

    private String email;
    private String password;
    private String name;
}
