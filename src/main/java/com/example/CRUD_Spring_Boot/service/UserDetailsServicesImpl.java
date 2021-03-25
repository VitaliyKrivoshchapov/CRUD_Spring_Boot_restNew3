package com.example.CRUD_Spring_Boot.service;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.repository.RoleRepository;
import com.example.CRUD_Spring_Boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServicesImpl implements UserDetailsService{
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    //@Autowired// попробоавть без аннтотации Работает без аннотации
    public UserDetailsServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(username);
        user.getAuthorities().size();
        //User user = userRepository.findByMail(username);чтобы заходиь по mail
        if (username == null) {
            throw new UsernameNotFoundException(String.format("User '%' not found ", username));
        }
        return user;
    }




}
