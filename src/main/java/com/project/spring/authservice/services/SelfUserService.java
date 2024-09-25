package com.project.spring.authservice.services;

import com.project.spring.authservice.exceptions.UserNotFoundException;
import com.project.spring.authservice.models.User;
import com.project.spring.authservice.repositories.TokenRepository;
import com.project.spring.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public User login(String email, String password) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with email" + email + " not found");
        }
        if (!bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            throw new UserNotFoundException("Wrong password for the user" + email);
        }
        return user.get();

    }

    @Override
    public void logout(String token) {

    }
}
