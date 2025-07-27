package com.lokesh.Quiz_Application_Project.seeder;

import com.lokesh.Quiz_Application_Project.model.Role;
import com.lokesh.Quiz_Application_Project.model.User;
import com.lokesh.Quiz_Application_Project.repository.RoleRepository;
import com.lokesh.Quiz_Application_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*********************************Running AdminSeeder...*********************************");
        if(userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not Found"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin@123"));
            admin.getRoles().add(adminRole);

            userRepository.save(admin);

            System.out.println("Seeded Default Admin User :" + "\nUsername : admin " + "\nPassword : admin@123");
        }
    }
}
