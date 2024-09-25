package com.project.spring.authservice.controllers;

import com.project.spring.authservice.dtos.LoginRequestDTO;
import com.project.spring.authservice.dtos.LogoutRequestDTO;
import com.project.spring.authservice.dtos.SignUpRequestDTO;
import com.project.spring.authservice.exceptions.UserNotFoundException;
import com.project.spring.authservice.models.Token;
import com.project.spring.authservice.models.User;
import com.project.spring.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok
                (userService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword(), signUpRequestDTO.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDTO loginRequestDTO) throws UserNotFoundException {
        return ResponseEntity.ok(userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) throws Exception {
        userService.logout(logoutRequestDTO.getTokenValue());
        return ResponseEntity.ok().build();
    }

}
