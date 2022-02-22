package com.example.projectshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private long id;
    private LocalDateTime dateCreated;
    private User user;

    private List<Device> deviceList;

    public ShoppingCart() {
    }

    public ShoppingCart(LocalDateTime dateCreated, User user) {
        this.dateCreated = dateCreated;
        this.user = user;
        this.deviceList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
