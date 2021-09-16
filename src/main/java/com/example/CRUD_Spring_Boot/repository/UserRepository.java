package com.example.CRUD_Spring_Boot.repository;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
//public interface UserRepository extends CrudRepository<User, Long> {
public interface UserRepository extends JpaRepository<User, Long> {


    //Optional<User> findByFirstName(String firstName);
    User findByFirstName(String username);
    User findByMail(String mail);
    User findUserById(Long id);
    //Role getByFirstName(String name);


}

