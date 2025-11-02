package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
