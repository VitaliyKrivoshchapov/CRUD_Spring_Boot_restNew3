package com.example.CRUD_Spring_Boot.repository;

import com.example.CRUD_Spring_Boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository  extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    //Role findByName(String role);
}
