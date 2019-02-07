package com.anoush.authentication.controller;

import com.anoush.authentication.model.Country;
import com.anoush.authentication.model.Role;
import com.anoush.authentication.model.RoleName;
import com.anoush.authentication.repository.CountryRepository;
import com.anoush.authentication.repository.RoleRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final CountryRepository countryRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, CountryRepository countryRepository) {
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (RoleName role : RoleName.values()) {
            Role role1 = new Role();
            role1.setId(UUID.randomUUID().toString());
            role1.setName(role);
//            roleRepository.save(role1);
        }
        System.out.println("printing all users...");
//        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        List<Country> obj = Arrays.asList(mapper.readValue(new File("src/main/resources/countries.json"), mapper.getTypeFactory().constructCollectionType(List.class, Country.class)));
//        Country[] countries = mapper.readValue(new File("src/main/resources/countries.json"), Country[].class);
//        for (Country country : countries) {
//            countryRepository.save(country);
//        }


    }
}