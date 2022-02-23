package com.example.projectshop.repository;

import com.example.projectshop.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device>  findByName(String name);
    void deleteByName(String name);
}
