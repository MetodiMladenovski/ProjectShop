package com.example.projectshop.service.impl;

import com.example.projectshop.model.Role;
import com.example.projectshop.model.User;
import com.example.projectshop.model.exceptions.InvalidArgumentsException;
import com.example.projectshop.model.exceptions.InvalidUserCredentialsException;
import com.example.projectshop.model.exceptions.PasswordsDoNotMatchException;
import com.example.projectshop.model.exceptions.UsernameAlreadyExistsException;
import com.example.projectshop.repository.UserRepository;
import com.example.projectshop.service.AuthService;

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
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password,String repeatPassword ,String email, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,password,email,role);
        return userRepository.save(user);

    }


}

