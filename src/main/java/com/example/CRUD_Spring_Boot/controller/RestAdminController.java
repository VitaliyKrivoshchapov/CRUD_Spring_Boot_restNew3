package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.service.UserService;
import com.example.CRUD_Spring_Boot.service.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restAdmin")
public class RestAdminController {

    private UserService userServicesImpl;
    public RestAdminController(UserService userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<User> findAllUsers(){

        //List<User> users = userServicesImpl.findAll();
        return userServicesImpl.findAll();
    }
    @PostMapping("/admin/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(User user, String role){
        userServicesImpl.saveUser(user,role);
    }

}
