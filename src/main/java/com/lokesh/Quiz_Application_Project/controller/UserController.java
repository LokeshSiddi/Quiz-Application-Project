package com.lokesh.Quiz_Application_Project.controller;

import com.lokesh.Quiz_Application_Project.dto.UserDto;
import com.lokesh.Quiz_Application_Project.model.User;
import com.lokesh.Quiz_Application_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto()); // UserDto -> User Data Transfer Object
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        userService.register(user);
        return "redirect:/auth/login?registered";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDto()); // UserDto -> User Data Transfer Object
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        String token = userService.verify(user);
        System.out.println(user);
        if("Verification Failed".equals(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

}
