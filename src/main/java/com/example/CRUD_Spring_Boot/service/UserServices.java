package com.example.CRUD_Spring_Boot.service;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired// попробоавть без аннтотации Работает без аннотации
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User saveUser(User user){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);}




    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername  работает метод из метода");
        User user = userRepository.findByFirstName(username);
        if (username == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
