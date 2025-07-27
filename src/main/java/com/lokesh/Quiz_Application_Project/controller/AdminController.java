package com.lokesh.Quiz_Application_Project.controller;

import com.lokesh.Quiz_Application_Project.model.User;
import com.lokesh.Quiz_Application_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    // Can Implement Admin Dashboard
    // { Insert, Update, Delete url's , etc..}
    // You can Create more ADMIN accounts with ADMIN Authorities

    @Autowired
    private UserService userService;

    @PostMapping("/create-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createAdmin(User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAdminUser(user));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
