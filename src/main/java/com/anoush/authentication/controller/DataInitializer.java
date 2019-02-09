package com.anoush.authentication.controller;

import com.anoush.authentication.model.Country;
import com.anoush.authentication.model.Role;
import com.anoush.authentication.model.RoleName;
import com.anoush.authentication.repository.CountryRepository;
import com.anoush.authentication.repository.RoleRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final RoleRepository roleRepository;

    private final CountryRepository countryRepository;

    @Value("${anoush.app.mongo.initialize.data}")
    private boolean load;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, CountryRepository countryRepository) {
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (load) {
            logger.info("Loading Data.");
            addRoles();
            addCountries();
        } else {
            logger.info("Data loading is turned off.");
        }
    }

    private void addCountries() throws java.io.IOException {
        logger.info("Adding Countries.");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Country[] countries = mapper.readValue(new File("src/main/resources/countries.json"), Country[].class);
        int i = 0;
        for (Country country : countries) {
            countryRepository.save(country);
            i++;
            if (i == countries.length) logger.info("Added all the countries to the database.");
        }
    }

    private void addRoles() {
        logger.info("Adding Roles.");
        int i = 0;
        for (RoleName role : RoleName.values()) {
            Role role1 = new Role();
            role1.setId(UUID.randomUUID().toString());
            role1.setName(role);
            roleRepository.save(role1);
            i++;
            if (i == RoleName.values().length) logger.info("Added all the roles to the database.");
        }
    }
}