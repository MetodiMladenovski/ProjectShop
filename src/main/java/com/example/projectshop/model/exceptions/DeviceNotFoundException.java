package com.example.projectshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException(Long id) {
        super(String.format("Device with id: %d was not found", id));
    }
}
