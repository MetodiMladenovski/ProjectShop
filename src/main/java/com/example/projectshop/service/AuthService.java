package com.example.projectshop.service;


import com.example.projectshop.model.User;

public interface AuthService {
    User login(String username, String password);
}
