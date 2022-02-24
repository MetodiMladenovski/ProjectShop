package com.example.projectshop.service;


import com.example.projectshop.model.Role;
import com.example.projectshop.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password,String repeatPassword,String email, Role role);
}
