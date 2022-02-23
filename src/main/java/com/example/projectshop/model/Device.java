package com.example.projectshop.model;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNumberId;

    private String name;

    private Double price;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    private Integer stock;

    public Device() {
    }

    public Device(String name, Double price, String description, Category category, Manufacturer manufacturer, Integer stock) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Long serialNumberId) {
        this.serialNumberId = serialNumberId;
    }
}
