package com.example.projectshop.service;

import com.example.projectshop.model.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword,String email, Role role);
}
