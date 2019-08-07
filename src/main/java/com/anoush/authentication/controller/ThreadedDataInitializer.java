package com.anoush.authentication.controller;

import com.anoush.authentication.model.Role;
import com.anoush.authentication.model.RoleName;
import com.anoush.authentication.model.User;
import com.anoush.authentication.repository.RoleRepository;
import com.anoush.authentication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created using IntelliJ IDEA
 * User: Manoj Chaulagain
 * Date: 2019-04-28
 * Time: 12:35
 */
@Slf4j
public class ThreadedDataInitializer implements Runnable {

   /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

   /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final int startIndex;
    private final int range;
    private final PasswordEncoder passwordEncoder;
   /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

    public ThreadedDataInitializer(RoleRepository roleRepository, UserRepository userRepository, int startIndex, int range, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.startIndex = startIndex;
        this.range = range;
        this.passwordEncoder = passwordEncoder;
    }

   /*--------------------------------------------
    |   P U B L I C    A P I    M E T H O D S   |
    ============================================*/

    @Override
    public void run() {
        try {
            IntStream.range(startIndex, startIndex + range).forEach(i -> {
                for (RoleName roleName : RoleName.values()) {
                    Optional<Role> savedRoleOptional = roleRepository.findByName(roleName);
                    if (savedRoleOptional.isPresent()) {
                        Role savedRole = savedRoleOptional.get();
                        switch (savedRole.getName()) {
                            case ROLE_PM:
                                addUser(savedRole, "Dipak Adhikari", "dipak.adhikari", "bjaydip.1992" + "@gmail.com");
                                break;
                            case ROLE_USER:
                                addUser(savedRole, "Menuka Dangal", "menuka.dangal", "tikaram.phuyal1" + "@gmail.com");
                                break;
                            case ROLE_ADMIN:
                                addUser(savedRole, "Manoj Chaulagain", "manoj.chaulagain", "chaulagainmanoj45" + "@gmail.com");
                                break;
                        }
                    }
                    if (i == startIndex + range - 1) log.info("Added all the roles to the database.");
                }
            });

        } catch (Exception ex) {
            log.error("Could not complete thread execution " + Thread.currentThread().getName() + ex);
        }
    }

   /*--------------------------------------------
    |    N O N - P U B L I C    M E T H O D S   |
    ============================================*/

    private void addUser(Role savedRole, String name, String userName, String email) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(savedRole);
        userRepository.save(
                new User(
                        name,
                        name,
                        userName,
                        email,
                        passwordEncoder.encode("N1e2p3al!"),
                        roleSet
                )
        );
        log.info("Added User with name= " + name + " the roles to the database.");
    }

   /*--------------------------------------------
    |   A C C E S S O R S / M O D I F I E R S   |
    ============================================*/

}
