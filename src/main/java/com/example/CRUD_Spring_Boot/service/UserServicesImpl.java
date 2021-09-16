package com.example.CRUD_Spring_Boot.service;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.repository.RoleRepository;
import com.example.CRUD_Spring_Boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServicesImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Transactional
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
    public void upDateUser(User user, String roles) {
        User newUser = userRepository.getOne(user.getId());
        Set<Role> roleSet = newUser.getRoles();

        if ((roles != null) & (roles.contains("ADMIN")))
            roleSet.add(roleRepository.findRoleById(1L)); //

        if ((roles != null) & (roles.contains("USER")))
            roleSet.add(roleRepository.findRoleById(2L)); //

        user.setPassword(changePass(user.getId(), user.getPassword()));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    private String changePass(Long id, String password) {
        if (password != "") {
            return bCryptPasswordEncoder.encode(password);
        }
        return findById(id).getPassword();
    }

    @Transactional
    public void saveUser(User user) {
        String rol = user.getRoleForHTML();

        Set<Role> roles = new HashSet<>();

        if (rol.contains("ADMIN"))
            roles.add(roleRepository.getRoleById(1L));
        if (rol.contains("USER"))
            roles.add(roleRepository.findRoleById(2L));
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public String getRolByName(String name) {
        User user = userRepository.findByFirstName(name);
        return user.getRoleStr(user);
    }
    public  Role findRoleObjectByName(String name){
        User user = userRepository.findByFirstName(name);
        String role = getRolByName(user.getFirstName());

        if (role.contains("ADMIN"))  return roleRepository.getRoleById(1l);
        if (role.contains("USER"))  return roleRepository.getRoleById(2l);
        return  null;

    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
