package com.example.CRUD_Spring_Boot.service;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    void upDateUser(User user,String roles);
    void saveUser(User user);
    Role getRolById(Long id);
    List<Role> getAllRoles();
    }
