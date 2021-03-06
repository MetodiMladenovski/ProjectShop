package com.example.projectshop.service;

import com.example.projectshop.model.Role;
import com.example.projectshop.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String email, Role role);
    UserDetails loadUserByUsername(String username);
}
