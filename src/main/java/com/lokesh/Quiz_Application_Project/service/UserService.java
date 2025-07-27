package com.lokesh.Quiz_Application_Project.service;

import com.lokesh.Quiz_Application_Project.exception.UserAlreadyExistException;
import com.lokesh.Quiz_Application_Project.exception.UsernameAlreadyExistException;
import com.lokesh.Quiz_Application_Project.model.Role;
import com.lokesh.Quiz_Application_Project.model.User;
import com.lokesh.Quiz_Application_Project.repository.RoleRepository;
import com.lokesh.Quiz_Application_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()) {
            if(passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword()))
                throw new UserAlreadyExistException("User Already Existed");
            throw new UsernameAlreadyExistException("Username Already Taken");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default Role not Found"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(userRole);

        userRepository.save(user);
    }

    public User createAdminUser(User admin) {

        Optional<User> existingAdmin = userRepository.findByUsername(admin.getUsername());

        if(existingAdmin.isPresent()) {
            if(passwordEncoder.matches(admin.getPassword(), existingAdmin.get().getPassword()))
                throw new RuntimeException("Admin Already Existed");
            throw new RuntimeException("Username Already Taken");
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Admin Role not Found"));

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.getRoles().add(adminRole);

        return userRepository.save(admin);
    }

    public String verify(User user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "Verification Failed";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
