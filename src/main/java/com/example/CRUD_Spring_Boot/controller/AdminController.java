package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.service.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping("/admin")
public class AdminController{

    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/admin")
    public String findAllUsers(Model model){
        List<User> users = userServices.findAll();
        model.addAttribute("users",users);
        return "admin/user";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServices.findById(id));
        return "admin/userGetUserById";
    }

    @GetMapping("/newUser")
    public String newUserForm(User user){
        System.out.println("А запускается ли у меня Get  Run форма работает");
        return "admin/newUser";
    }
    @PostMapping("")
    public String newUser(User user){
        System.out.println(" А запускается ли  меня newUser");
        userServices.saveUser(user);
        return  "redirect:/admin/user";
    }

    @GetMapping("/delUser/{id}")
    public String delUser(@PathVariable("id") Long id){
        userServices.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm (@PathVariable("id") Long id, Model model){
        User user = userServices.findById(id);
        model.addAttribute("user",user);
        return "/admin/updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user){

        userServices.saveUser(user);
        return "redirect:/user";
    }
}

