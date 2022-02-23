package com.example.projectshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop_users")
public class User {

    @Id
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> userCarts;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String password, String email,Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userCarts = new ArrayList<>();
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ShoppingCart> getUserCart() {
        return userCarts;
    }

    public void setUserCart(List<ShoppingCart> userCart) {
        this.userCarts = userCart;
    }
}
