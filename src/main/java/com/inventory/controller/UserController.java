package com.inventory.controller;

import com.inventory.model.User;
import com.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // POST: Yeni Kullanıcı Ekle
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // GET: Tüm Kullanıcıları Listele
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}