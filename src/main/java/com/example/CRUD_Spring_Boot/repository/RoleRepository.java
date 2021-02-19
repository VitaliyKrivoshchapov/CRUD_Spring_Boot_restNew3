package com.example.CRUD_Spring_Boot.repository;

import com.example.CRUD_Spring_Boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getByRole(String roleName);
}
