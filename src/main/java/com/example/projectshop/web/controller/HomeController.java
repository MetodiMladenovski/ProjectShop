package com.example.projectshop.web.controller;

import com.example.projectshop.service.DeviceService;
import com.example.projectshop.service.impl.DeviceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    DeviceService deviceService;

    public HomeController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        model.addAttribute("devices", deviceService.findAll());
        return "glasses";
    }
}
