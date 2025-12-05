package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;




@Controller
public class ProductController {
    final private UserService userService;
    public ProductController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        return "admin/product/show";
    }
     @RequestMapping("/admin/product/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }


}
