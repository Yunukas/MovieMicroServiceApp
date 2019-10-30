package com.yy.user.controller;

import com.yy.user.entity.User;
import com.yy.user.repository.UserRepository;
import com.yy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId){
        return userService.getUserWithId(userId);
    }
}
