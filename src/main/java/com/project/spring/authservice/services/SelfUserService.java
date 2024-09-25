package com.project.spring.authservice.services;

import com.project.spring.authservice.exceptions.UserNotFoundException;
import com.project.spring.authservice.models.Token;
import com.project.spring.authservice.models.User;
import com.project.spring.authservice.repositories.TokenRepository;
import com.project.spring.authservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class SelfUserService implements UserService {

    private final UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SelfUserService(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(String email, String password, String name) {
        User signedUpUser = new User();
        signedUpUser.setEmail(email);
        signedUpUser.setPassword(bCryptPasswordEncoder.encode(password));
        signedUpUser.setName(name);

        return userRepository.save(signedUpUser);

    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        if (!bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            throw new UserNotFoundException("Wrong password for the user " + email);
        }

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
        token.setExpiryDate(expiryDate);
        token.setUser(user.get());

        return tokenRepository.save(token);

    }

    @Override
    public void logout(String token) throws Exception {
        Optional<Token> tokenOptional = tokenRepository.findByTokenValue(token);
        if (tokenOptional.isEmpty()) {
            throw new Exception("Token does not exists");
        }
        Token existingToken = tokenOptional.get();
        existingToken.setDeleted(true);
        tokenRepository.save(existingToken);
    }
}
