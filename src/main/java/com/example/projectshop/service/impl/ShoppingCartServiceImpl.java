package com.example.projectshop.service.impl;

import com.example.projectshop.model.Device;
import com.example.projectshop.model.ShoppingCart;
import com.example.projectshop.model.User;
import com.example.projectshop.model.exceptions.DeviceAlreadyInShoppingCartException;
import com.example.projectshop.model.exceptions.DeviceNotFoundException;
import com.example.projectshop.model.exceptions.ShoppingCartNotFoundException;
import com.example.projectshop.model.exceptions.UserNotFoundException;
import com.example.projectshop.repository.ShoppingCartRepository;
import com.example.projectshop.repository.UserRepository;
import com.example.projectshop.service.DeviceService;
import com.example.projectshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final DeviceService deviceService;
    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, DeviceService deviceService, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.deviceService = deviceService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Device> listAllDevicesInShoppingCart(Long cartId) {
        if(!shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return shoppingCartRepository.findById(cartId).get().getDeviceList();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository.findByUser(user).orElseGet(() ->
        {
            ShoppingCart shoppingCart = new ShoppingCart(user);
            return this.shoppingCartRepository.save(shoppingCart);
        });
    }

    @Override
    public ShoppingCart addDeviceToShoppingCart(String username, Long deviceId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Device device = this.deviceService.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        if(shoppingCart.getDeviceList().stream().anyMatch(d -> d.getSerialNumberId().equals(deviceId)))
            throw new DeviceAlreadyInShoppingCartException(deviceId, username);

        shoppingCart.getDeviceList().add(device);
        return shoppingCartRepository.save(shoppingCart);
    }
}
