package com.anoush.authentication.controller;

import com.anoush.authentication.model.Role;
import com.anoush.authentication.model.RoleName;
import com.anoush.authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        for (RoleName role : RoleName.values()) {
            Role role1 = new Role();
            role1.setId(UUID.randomUUID().toString());
            role1.setName(role);
            roleRepository.save(role1);
        }
        System.out.println("printing all users...");
//        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}