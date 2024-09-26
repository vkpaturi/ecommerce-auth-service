package com.project.spring.authservice.repositories;

import com.project.spring.authservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenValueAndDeleted(String token, boolean deleted);
    Optional<Token> findByTokenValueAndDeletedEqualsAndExpiryDateGreaterThan(String token, boolean deleted, Date expiryDate);
    Token save(Token token);

}
