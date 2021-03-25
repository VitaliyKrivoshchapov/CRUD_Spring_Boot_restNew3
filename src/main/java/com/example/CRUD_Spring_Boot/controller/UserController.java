package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.service.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServicesImpl userServicesImpl;

    @Autowired
    public UserController(UserServicesImpl userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, Principal principal) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) a.getPrincipal();
        model.addAttribute("userGotIn", user);
        model.addAttribute("user", userServicesImpl.findById(id));
        return "user/index";
    }

    @GetMapping()
    public String user(@AuthenticationPrincipal User user, Model model){

        model.addAttribute("user",user);
        model.addAttribute("roles",user.getRoles());
        return "userIndex";
    }

}
