package com.example.CRUD_Spring_Boot.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DtoUser {
    private String firstName;
    private String lastName;
    private int age;
    private String mail;
    private String password;
    private String roleForHTML;
    //private Set<Role> roles;

}
