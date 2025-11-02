package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        return "hello";
    }

    // Back to user management page
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.handleFindAll();
        model.addAttribute("users1", users);
        return "/admin/user/table-user";
    }

    // View user detail page
    @RequestMapping("/admin/user/{id}")
    public String getUserDetail(Model model, @PathVariable Long id) {
        User user = this.userService.handleFindById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "/admin/user/show";
    }

    // Get create user page
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    // Handle create user request
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    // Get update user page
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/update";
    }
}
