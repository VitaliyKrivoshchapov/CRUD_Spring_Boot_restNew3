package com.example.CRUD_Spring_Boot.DTO;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class MapDtoToUser {
    //из Dto to user
    public User mapDtoToUser(DtoUser dtoUser) {
        User user = new User();

        user.setFirstName(dtoUser.getFirstName());
        user.setLastName(dtoUser.getLastName());
        user.setAge(dtoUser.getAge());
        user.setMail(dtoUser.getMail());
        user.setPassword(dtoUser.getPassword());

        Role role = new Role();
        Set<Role> roleSet = new HashSet<>();

        if (dtoUser.getRoleForHTML().contains("ADMIN")) {
            role.setRole("ADMIN");//roleRepository
            role.setId(1L);
            roleSet.add(role);//вот тут

        }
        if (dtoUser.getRoleForHTML().contains("USER")) {
            role.setRole("USER");
            role.setId(2L);
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        return user;
    }
}
