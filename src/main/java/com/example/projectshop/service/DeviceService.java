package com.example.projectshop.service;

import com.example.projectshop.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    List<Device> findAll();

    Optional<Device> findById(Long id);

    Optional<Device> findByName(String name);

    Optional<Device> save(String name,String description, Double price,Integer stock, Long categoryId, Long manufacturerId, String image);

    Optional<Device> edit(Long DeviceId, String name,String description, Double price,Integer stock, Long categoryId, Long manufacturerId, String image);

    void deleteById(Long id);

    List<Device> filterByCategory(Long categoryId);

    List<Device> filterByPrice(Double price);

    List<Device> filterByCategoryAndPrice(Long categoryId, Double price);

}
