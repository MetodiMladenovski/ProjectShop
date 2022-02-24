package com.example.projectshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class DeviceAlreadyInShoppingCartException extends RuntimeException{
    public DeviceAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Device with id: %d already exists in shopping cart for user with username %s", id, username));
    }

}
