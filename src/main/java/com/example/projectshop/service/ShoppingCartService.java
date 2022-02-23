package com.example.projectshop.service;

import com.example.projectshop.model.Device;
import com.example.projectshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Device> listAllDevicesInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addDeviceToShoppingCart(String username, Long deviceId);
}
