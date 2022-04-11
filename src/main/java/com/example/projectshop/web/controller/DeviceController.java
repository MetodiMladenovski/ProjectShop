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
@RequestMapping("/devices")
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
    public String getDevicePage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) Long category,
                                @RequestParam(required = false) Double priceFilter,
                                Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        List<Device> devices;
        if(category != null && category != 0 && priceFilter != null){
            devices = this.deviceService.filterByCategoryAndPrice(category, priceFilter);
        } else if(priceFilter != null){
            devices = this.deviceService.filterByPrice(priceFilter);
        }
        else if (category != null && category != 0) {
            devices = this.deviceService.filterByCategory(category);
        }
        else {
            devices = this.deviceService.findAll();
        }

        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("devices", devices);
        model.addAttribute("categories", categories);

        return "devices";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDevice(@PathVariable Long id){
        this.deviceService.deleteById(id);
        return "redirect:/devices";
    }

    @GetMapping("/add-device")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDevicePage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        // model.addAttribute("bodyContent", "add-device");
        return "add-device";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model){
        if(this.deviceService.findById(id).isPresent()){
            Device device = this.deviceService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("device", device);
            model.addAttribute("bodyContent", "add-product");
            return "add-device";
        }
        return "redirect:/devices?error=ProductNotFound";
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
        return "redirect:/devices";
    }
//
//    @PostMapping("/get-category-devices")
//    public String getDevicePage(@RequestParam Long category, @RequestParam(required = false) String error, Model model){
//        if(error != null && !error.isEmpty()){
//            model.addAttribute("hasError", true);
//            model.addAttribute("error",error);
//        }
//        List<Device> devices = this.deviceService.filterByCategory(category);
//        List<Category> categories = this.categoryService.listCategories();
//        model.addAttribute("devices", devices);
//        model.addAttribute("categories", categories);
//        return "devices";
//    }
}
