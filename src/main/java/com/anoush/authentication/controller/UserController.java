package com.anoush.authentication.controller;

import com.anoush.authentication.utilities.GraphQlUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {

    private GraphQL graphQL;

    @Autowired
    public UserController(GraphQlUtility graphQlUtility) throws IOException {
        this.graphQL = graphQlUtility.createGraphQlObject();
    }

    @GetMapping("/test/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return ">>> User Contents!";
    }

    @GetMapping("/test/pm")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String projectManagementAccess() {
        return ">>> Board Management Project";
    }

    @GetMapping("/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return ">>> Admin Contents";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUsers(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return ResponseEntity.ok(result.getData());
    }

//    @GetMapping("/users/{userName}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> getUsers(@PathVariable String userName) {
//        Optional<User> userOptional = userRepository.findByUsername(userName);
//        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
}