package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.DTO.DtoUser;
import com.example.CRUD_Spring_Boot.DTO.MapDtoToUser;
import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.service.UserService;
import com.example.CRUD_Spring_Boot.service.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restAdmin")
public class RestAdminController {

    private UserService userService;

    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(DtoUser dtoUser)
    {

        MapDtoToUser mapDtoToUser = new MapDtoToUser();
        User user = mapDtoToUser.mapDtoToUser(dtoUser);

        userService.saveUser(user);

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/del")
    public void delUser(Long id) {
        userService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/edit")
    public void editUser(User user) {
               userService.upDateUser(user);

    }
}
