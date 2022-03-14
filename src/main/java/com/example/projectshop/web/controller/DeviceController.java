package com.example.projectshop.web.controller;


import com.example.projectshop.model.Category;
import com.example.projectshop.model.Device;
import com.example.projectshop.model.Manufacturer;
import com.example.projectshop.service.CategoryService;
import com.example.projectshop.service.DeviceService;
import com.example.projectshop.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class DeviceController {
    private final DeviceService deviceService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public DeviceController(DeviceService deviceService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.deviceService = deviceService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }
    @GetMapping
    public String getDevicePage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        List<Device> devices = this.deviceService.findAll();
        model.addAttribute("devices", devices);
        model.addAttribute("bodyContent", devices);
                         //TODO: IMPL LATER
        return " ";
    }

    @PostMapping("/{serialNumberId}/edit")
    public String deleteDevice(@PathVariable Long serialNumberId){
        this.deviceService.deleteById(serialNumberId);
        return " ";
    }

    @GetMapping("/add-form")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDevicePage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-device");
        return " ";
    }

    @PostMapping("/add")
    public String saveDevice(@RequestParam(required = false) Long serialNumberId,
                             @RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam String description,
                             @RequestParam Long category,
                             @RequestParam Long manufacturer,
                             @RequestParam Integer stock,
                             @RequestParam String image
                             ){
        if(serialNumberId != null){
            this.deviceService.edit(serialNumberId, name, description, price, stock, category,manufacturer, image);
        } else {
            this.deviceService.save(name,description, price, stock,category,manufacturer, image);
        }
        return " ";
    }
}
