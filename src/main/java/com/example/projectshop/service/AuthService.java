package com.example.projectshop.service;

import org.springframework.security.core.userdetails.User;

public interface AuthService {
    User login(String username, String password);
}
