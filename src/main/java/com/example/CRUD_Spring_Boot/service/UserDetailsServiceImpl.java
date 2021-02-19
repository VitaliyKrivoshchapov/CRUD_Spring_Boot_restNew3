/*
package com.example.CRUD_Spring_Boot.service;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
*/
/*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Прежде чем запустить userDetailservices");
        User user = userRepository.findByFirstName(s);

        return user;
    }*//*


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("[thyz rfrfz-nj");
        return userRepository.findByFirstName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
    }
}
*/
