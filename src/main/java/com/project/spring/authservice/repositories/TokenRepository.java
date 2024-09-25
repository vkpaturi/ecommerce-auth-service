package com.project.spring.authservice.repositories;

import com.project.spring.authservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);

}
