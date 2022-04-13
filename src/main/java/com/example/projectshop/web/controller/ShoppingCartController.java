package com.example.projectshop.web.controller;

import com.example.projectshop.model.Device;
import com.example.projectshop.model.ShoppingCart;
import com.example.projectshop.model.User;
import com.example.projectshop.service.ShoppingCartService;
import com.example.projectshop.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("devices", this.shoppingCartService.listAllDevicesInShoppingCart(shoppingCart.getId()));
        return "shopping-cart";
    }

    @PostMapping("/add-device/{id}")
    public String addDeviceToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) this.userService.loadUserByUsername(req.getRemoteUser());
            this.shoppingCartService.addDeviceToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/remove/{id}")
    public String removeDeviceFromShoppingCart(@PathVariable Long id, HttpServletRequest req){
        this.shoppingCartService.removeDeviceFromShoppingCart(id, req.getRemoteUser());
        return "redirect:/shopping-cart";
    }
}
