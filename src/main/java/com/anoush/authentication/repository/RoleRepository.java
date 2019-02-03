package com.anoush.authentication.repository;

import java.util.Optional;

import com.anoush.authentication.model.Role;
import com.anoush.authentication.model.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}