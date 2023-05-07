package com.example.demo.dao.auth;

import com.example.demo.model.auth.ERole;
import com.example.demo.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByName(ERole name);
}
