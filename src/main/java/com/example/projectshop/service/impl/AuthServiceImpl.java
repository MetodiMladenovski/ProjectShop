package com.example.projectshop.service.impl;

import com.example.projectshop.model.exceptions.InvalidArgumentsException;
import com.example.projectshop.model.exceptions.InvalidUserCredentialsException;
import com.example.projectshop.repository.UserRepository;
import com.example.projectshop.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
     //   return userRepository.findByUsernameAndPassword(username,
     //           password).orElseThrow(InvalidUserCredentialsException::new);
        return null;
    }
}

