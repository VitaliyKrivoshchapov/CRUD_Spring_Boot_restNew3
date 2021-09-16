package com.example.CRUD_Spring_Boot.repository;

import com.example.CRUD_Spring_Boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository  extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    Role getRoleById(Long id);
    List<Role> findAll();


}
