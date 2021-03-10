package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.Role;
import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.repository.RoleRepository;
//import com.example.CRUD_Spring_Boot.service.RoleServices;
import com.example.CRUD_Spring_Boot.service.UserServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController{

    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public String findAllUsers(Model model,Principal principal){
        List<User> users = userServices.findAll();
        model.addAttribute("users",users);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",a.getName());

//---------------------------
        User user = (User) a.getPrincipal();
        model.addAttribute("userGotIn", user);
//--------------------

        Set<Role> role = new HashSet<>();
        role.add(userServices.getRolById(1L));
        role.add(userServices.getRolById(2l));
        model.addAttribute("newUser",new User());
        model.addAttribute("roles",role);

        return "admin/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServices.findById(id));
        return "admin/userGetUserById";
    }
/*
    @GetMapping("/newUser") //Работает
    public String newUserForm(@ModelAttribute("user")  User user){
        return "admin/newUser";
    }*/
/*    @GetMapping("/newUser")
    public  String newUserForm(ModelMap modelMap){
        return "admin/newUser";
    }*/

    @PostMapping("/newUser")
    public  String newUser(@ModelAttribute("newUser") User user, @RequestParam(value = "setRoles",required = false) String roles){
        if (roles == null) roles = " ";
        userServices.saveUser(user,roles);
        //System.out.println(roles);
        return  "redirect:/admin";
    }
    @GetMapping("/delUser/{id}")
    public String delUser(@PathVariable("id") Long id){
        userServices.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm (@PathVariable("id") Long id, Model model){
        System.out.println("Работаю в ");
        User user = userServices.findById(id);
        Set<Role> role = new HashSet<>();
        role.add(userServices.getRolById(1L));
        role.add(userServices.getRolById(2l));
        model.addAttribute("roles",role);
        model.addAttribute("user",user);
        return "/admin/index" ;
        /*return "/admin/updateUser";*/
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "setRoles",required = false) String roles){
        System.out.println(roles);
        // Authentication a = SecurityContextHolder.getContext().getAuthentication();
       //System.out.println(a);
       System.out.println("User get from PostMapping"+user);

        //userServices.upDateUser(user);
        userServices.upDateUser(user,roles);
        return "redirect:/admin";
    }
}

