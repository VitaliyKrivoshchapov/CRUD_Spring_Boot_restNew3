package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userServices.findById(id));
        return "userGetUserById";
    }

    @GetMapping("/users")
    public String findAllUsers(Model model){
        List<User> users = userServices.findAll();
        //System.out.println(users.toString());
        //User user = users.get(1);
        //System.out.println(userServices.loadUserByUsername("1"));
        model.addAttribute("users",users);
        return "user";
    }

    @GetMapping("/newUser")
    public String newUserForm(User user){
        return "newUser";
    }
    @PostMapping("/newUser")

    public String newUser(User user){
        userServices.saveUser(user);
        return  "redirect:/users";
    }

    @GetMapping("/delUser/{id}")
    public String delUser(@PathVariable("id") Long id){
        userServices.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm (@PathVariable("id") Long id, Model model){
        System.out.println("GetMapping user update");
        User user = userServices.findById(id);
        model.addAttribute("user",user);
        return "/updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user){

    userServices.saveUser(user);
    return "redirect:/users";
    }
}
