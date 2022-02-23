package com.example.projectshop.model;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serialNumberId;

    private String name;

    private double price;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    private int stock;

    public Device() {
    }

    public Device(String name, double price, String description, Category category, Manufacturer manufacturer, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(long serialNumberId) {
        this.serialNumberId = serialNumberId;
    }
}
