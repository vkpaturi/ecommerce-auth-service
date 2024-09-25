package com.project.spring.authservice.services;

import com.project.spring.authservice.exceptions.UserNotFoundException;
import com.project.spring.authservice.models.User;
import com.project.spring.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SelfUserService implements UserService {

    private UserRepository userRepository;

    @Autowired
    public SelfUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signUp(String email, String password, String name) {
        User signedUpUser = new User();
        signedUpUser.setEmail(email);
        signedUpUser.setPassword(password);
        signedUpUser.setName(name);

        return userRepository.save(signedUpUser);

    }

    @Override
    public User login(String email, String password) throws UserNotFoundException {
        Optional<User> userOptional= userRepository.findByEmailandPassword(email, password);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Email or Password of the user is not matching");
        }
        return userOptional.get();
    }

    @Override
    public void logout(String token) {

    }
}
