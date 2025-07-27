package com.lokesh.Quiz_Application_Project.seeder;

import com.lokesh.Quiz_Application_Project.model.Role;
import com.lokesh.Quiz_Application_Project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class RoleSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName("ROLE_USER").isEmpty())
            roleRepository.save(new Role(null, "ROLE_USER"));

        if(roleRepository.findByName("ROLE_ADMIN").isEmpty())
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
    }
}
