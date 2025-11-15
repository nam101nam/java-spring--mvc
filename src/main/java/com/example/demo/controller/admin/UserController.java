package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.User;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    final private UserService userService;
    final private UploadService uploadService;
    final private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
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
        return "/admin/user/show";
    }

    // View user detail page
    @RequestMapping("/admin/user/{id}")
    public String getUserDetail(Model model, @PathVariable Long id) {
        User user = this.userService.handleFindById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "/admin/user/detail";
    }

    // Get create user page
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    // Handle create user request
    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User user,
            @RequestParam("hoidanitFile") MultipartFile file) {

        String fileName = this.uploadService.handleUploadFile(file, "avatar");
        user.setAvatar(fileName);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(this.userService.handleFindRoleByName(user.getRoles().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    // Get update user page
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable Long id) {
        User currentUser = this.userService.handleFindById(id);
        model.addAttribute("newUser", currentUser);
        return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(Model model, @ModelAttribute("newUser") User user) {
        User updateUser = this.userService.handleFindById(user.getId());
        if (updateUser != null) {
            updateUser.setFullName(user.getFullName());
            updateUser.setPhone(user.getPhone());
            updateUser.setAddress(user.getAddress());
            this.userService.handleSaveUser(updateUser);

        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable Long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("user", new User());
        return "/admin/user/delete";
    }

    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.POST)
    public String deleteUserPage(Model model, @ModelAttribute("user") User user) {
        this.userService.handleDeleteById(user.getId());
        return "redirect:/admin/user";
    }

}
