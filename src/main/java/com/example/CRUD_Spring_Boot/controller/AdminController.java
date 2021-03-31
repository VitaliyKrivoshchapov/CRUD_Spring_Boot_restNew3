package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;
//import com.example.CRUD_Spring_Boot.service.RoleServices;
import com.example.CRUD_Spring_Boot.service.UserService;
import com.example.CRUD_Spring_Boot.service.UserServicesImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController{

    private final UserService userServices;
    public AdminController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public String findAllUsers(Model model){
        List<User> users = userServices.findAll();
        model.addAttribute("users",users);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",a.getName());
//---------------------------

        User user = (User) a.getPrincipal();
        model.addAttribute("userGotIn", user);
//--------------------
        model.addAttribute("newUser",new User());
        model.addAttribute("roles", userServices.getAllRoles());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServices.findById(id));
        return "admin/userGetUserById";
    }
    @GetMapping("/userIndex")
    public String user(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles",user.getRoles());
        return "admin/userIndex";
    }
    @PostMapping("/newUser")
    public  String newUser(@ModelAttribute("newUser") User user, @RequestParam(value = "setRoles",required = false) String roles){
        if (roles == null) roles = " ";
        userServices.saveUser(user,roles);
        return  "redirect:/admin";
    }
    @GetMapping("/delUser/{id}")
    public String delUser(@PathVariable("id") Long id){
        userServices.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm (@PathVariable("id") Long id, Model model){
        User user = userServices.findById(id);
        System.out.println(id);
        Set<Role> role = new HashSet<>();
        role.add(userServices.getRolById(1L));
        role.add(userServices.getRolById(2l));
        model.addAttribute("roles",role);
        model.addAttribute("user",user);
        return "/admin" ;
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "setRoles",required = false) String roles){
        userServices.upDateUser(user,roles);
        return "redirect:/admin";
    }
}

