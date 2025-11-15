package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Roles;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    final private UserRepository userRepository;
    final private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "Hello from service";
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        System.err.println(eric);
        return eric;
    }

    public List<User> handleFindByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<User> handleFindAll() {
        return this.userRepository.findAll();
    }

    public User handleFindById(long id) {

        return this.userRepository.findById(id);
    }

    public void handleDeleteById(long id) {
        this.userRepository.deleteById(id);
    }

    public Roles handleFindRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
