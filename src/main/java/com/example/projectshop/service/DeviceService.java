package com.example.projectshop.service;

import com.example.projectshop.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    List<Device> findAll();

    Optional<Device> findById(Long id);

    Optional<Device> findByName(String name);

    Optional<Device> save(String name,String description, Double price,Integer stock, Long categoryId, Long manufacturerId);

    Optional<Device> edit(Long DeviceId, String name,String description, Double price,Integer stock, Long categoryId, Long manufacturerId);

    void deleteById(Long id);

}
