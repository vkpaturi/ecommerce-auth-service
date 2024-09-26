package com.project.spring.authservice.services;

import com.project.spring.authservice.exceptions.UserNotFoundException;
import com.project.spring.authservice.models.Token;
import com.project.spring.authservice.models.User;

public interface UserService {
    User signUp(String email, String password, String name);
    Token login(String email, String password) throws UserNotFoundException;
    void logout(String token) throws Exception;
    User validateToken(String token) throws Exception;
}
