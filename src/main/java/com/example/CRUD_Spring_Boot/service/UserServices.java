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
public class UserServices implements UserDetailsService{


    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    //@Autowired// попробоавть без аннтотации Работает без аннотации
    public UserServices(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public void upDateUser(User user,String roles){
        //System.out.println(user.getRoles());
        User user2 = userRepository.getOne(user.getId());
        Set<Role> roleSet = user2.getRoles();

        if ((roles!=null)&(roles.contains("ADMIN")))
            roleSet.add(roleRepository.findRoleById(1L)); //
        if ((roles!=null)&(roles.contains("USER")))
            roleSet.add(roleRepository.findRoleById(2L)); //


        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Transactional
    public void saveUser(User user,String rolesString){
       //user.setPassword(passwordEncoder.encode(user.getPassword()));
      Set<Role> roles = new HashSet<>();

      if (rolesString.contains("ADMIN"))//попробова eqauals
              roles.add(roleRepository.findRoleById(1L));
      if (rolesString.contains("USER"))//попробова eqauals
              roles.add(roleRepository.findRoleById(2L));


        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(username);
        //User user = userRepository.findByMail(username);
        if (username == null) {
            throw new UsernameNotFoundException(String.format("User '%' not found ", username));
        }
        return user;
    }
    public Role getRolById(Long id){
        return roleRepository.findRoleById(id);
    }

}
