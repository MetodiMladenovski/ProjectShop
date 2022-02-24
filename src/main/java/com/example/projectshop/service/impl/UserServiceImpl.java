package com.example.projectshop.service.impl;

import com.example.projectshop.model.Role;
import com.example.projectshop.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.projectshop.model.exceptions.PasswordsDoNotMatchException;
import com.example.projectshop.model.exceptions.UsernameAlreadyExistsException;
import com.example.projectshop.repository.UserRepository;
import com.example.projectshop.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String email, Role role) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        //User user = new User(username, passwordEncoder.encode(password), email, role);
        //return userRepository.save(user);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     //   return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return null;
    }
}
