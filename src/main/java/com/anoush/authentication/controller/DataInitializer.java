package com.anoush.authentication.controller;

import com.anoush.authentication.model.Country;
import com.anoush.authentication.model.Role;
import com.anoush.authentication.model.RoleName;
import com.anoush.authentication.model.User;
import com.anoush.authentication.repository.CountryRepository;
import com.anoush.authentication.repository.RoleRepository;
import com.anoush.authentication.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final RoleRepository roleRepository;

    private final CountryRepository countryRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Value("${anoush.app.mongo.initialize.data}")
    private boolean load;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (load) {
            logger.info("Loading Data.");
            addUsers();
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

    private void addUsers() {
        logger.info("Adding Roles.");
        int i = 0;
        for (RoleName roleName : RoleName.values()) {
            Role role = new Role(roleName);
            Role savedRole = roleRepository.save(role);
            switch (savedRole.getName()) {
                case ROLE_PM:
                    addUser(savedRole, "Dipak Adhikari", "dipak.adhikari", "bjaydip.1992@gmail.com");
                    break;
                case ROLE_USER:
                    addUser(savedRole, "Menuka Dangal", "menuka.dangal", "tikaram.phuyal1@gmail.com");
                    break;
                case ROLE_ADMIN:
                    addUser(savedRole, "Manoj Chaulagain", "manoj.chaulagain", "chaulagainmanoj45@gmail.com");
                    break;
            }
            i++;
            if (i == RoleName.values().length) logger.info("Added all the roles to the database.");
        }
    }

    private void addUser(Role savedRole, String name, String userName, String email) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(savedRole);
        userRepository.save(
                new User(
                        name,
                        userName,
                        email,
                        passwordEncoder.encode("N1e2p3al!"),
                        roleSet
                )
        );
    }
}