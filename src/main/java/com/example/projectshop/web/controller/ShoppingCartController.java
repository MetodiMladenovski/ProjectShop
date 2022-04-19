package com.example.projectshop.web.controller;

import com.example.projectshop.model.ShoppingCart;
import com.example.projectshop.model.User;
import com.example.projectshop.model.stripe.ChargeRequest;
import com.example.projectshop.service.ShoppingCartService;
import com.example.projectshop.service.StripeService;
import com.example.projectshop.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Value("pk_test_51J9uezDwr6Ee0MykYoMez36xqtQ6z4UT1Tms9Fj0Uj4DJahOgMNsVxfxd39UrVn97VuIdtuERZ1jpFItLrQcxN7o00Jcg14CMR")
    private String stripePublicKey;

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final StripeService paymentService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, StripeService paymentService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.paymentService = paymentService;
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
        int amountInPennies = (int) Math.round(shoppingCart.getTotalPrice()*100);
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("amountInPennies", amountInPennies);
        model.addAttribute("devices", this.shoppingCartService.listAllDevicesInShoppingCart(shoppingCart.getId()));
        model.addAttribute("stripePublicKey", this.stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
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

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, HttpServletRequest req) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentService.charge(chargeRequest);

        this.shoppingCartService.clearShoppingCartAfterPayment(req.getRemoteUser());
        return "redirect:/shopping-cart";
    }

}
