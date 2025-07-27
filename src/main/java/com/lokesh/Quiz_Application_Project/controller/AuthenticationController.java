package com.lokesh.Quiz_Application_Project.controller;

import com.lokesh.Quiz_Application_Project.dto.UserDto;
import com.lokesh.Quiz_Application_Project.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
//@RequestMapping("/api")
@Tag(name = "Authenticate API")
public class AuthenticationController {

    @Autowired
    private UserDto userDto;

    @Autowired
    private UserService userService;

    /*@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto()); // UserDto -> User Data Transfer Object
        return "register";
    }*/

    /*@PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userService.register(user);
        return "redirect:/login?registered";
    }*/

    /*@GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDto()); // UserDto -> User Data Transfer Object
        return "login";
    }*/

    /*@Operation(summary = "Authenticate User and Return JWT Token")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String token = userService.verify(user);
        if(token.equals("Verification Failed"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username/Password");
        System.out.println(user);
        return ResponseEntity.ok(Map.of("token", token));
    }*/

}
