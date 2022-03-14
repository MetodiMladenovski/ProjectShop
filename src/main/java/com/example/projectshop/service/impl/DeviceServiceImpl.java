package com.example.projectshop.service.impl;

import com.example.projectshop.model.Category;
import com.example.projectshop.model.Device;
import com.example.projectshop.model.Manufacturer;
import com.example.projectshop.model.exceptions.CategoryNotFoundException;
import com.example.projectshop.model.exceptions.DeviceNotFoundException;
import com.example.projectshop.model.exceptions.ManufacturerNotFoundException;
import com.example.projectshop.repository.CategoryRepository;
import com.example.projectshop.repository.DeviceRepository;
import com.example.projectshop.repository.ManufacturerRepository;
import com.example.projectshop.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository,
                             ManufacturerRepository manufacturerRepository,
                             CategoryRepository categoryRepository) {
        this.deviceRepository = deviceRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id);
    }

    @Override
    public Optional<Device> findByName(String name) {
        return deviceRepository.findByName(name);
    }

    @Override
    public Optional<Device> save(String name, String description, Double price, Integer stock, Long categoryId, Long manufacturerId, String image) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Device device = new Device(name, price, description, category, manufacturer, stock, image);
        return Optional.of(deviceRepository.save(device));
    }

    @Override
    public Optional<Device> edit(Long DeviceId, String name, String description, Double price, Integer stock, Long categoryId, Long manufacturerId, String image) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Device device = deviceRepository.findById(DeviceId).orElseThrow(() -> new DeviceNotFoundException(DeviceId));

        device.setCategory(category);
        device.setManufacturer(manufacturer);
        device.setDescription(description);
        device.setName(name);
        device.setPrice(price);
        device.setStock(stock);
        device.setImage(image);

        return Optional.of(deviceRepository.save(device));
    }

    @Override
    public void deleteById(Long id) {
        this.deviceRepository.deleteById(id);
    }
}
