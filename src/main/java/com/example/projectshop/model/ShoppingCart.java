package com.example.projectshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {

    private long id;
    private LocalDateTime dateCreated;
    private User user;
    List<Device> deviceList;

    public ShoppingCart() {
    }
}
