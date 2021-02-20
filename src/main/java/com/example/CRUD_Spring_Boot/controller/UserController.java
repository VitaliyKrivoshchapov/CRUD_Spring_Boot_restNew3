package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.service.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        System.out.println("Работает getUserById из под юзера");
        model.addAttribute("user", userServices.findById(id));
        return "userGetUserById";
    }

/*    @GetMapping("/users")
    public String findAllUsers(Model model){
        List<User> users = userServices.findAll();
        model.addAttribute("users",users);
        return "user";
    }*/
/*
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
    }*/
}
