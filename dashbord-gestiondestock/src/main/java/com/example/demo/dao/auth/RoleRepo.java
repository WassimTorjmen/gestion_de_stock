package com.example.demo.dao;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByName(ERole name);
}