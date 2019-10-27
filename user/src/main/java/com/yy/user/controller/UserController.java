package com.yy.user.controller;

import com.yy.user.entity.User;
import com.yy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId){
        final Optional<User> optUser = userRepository.findById(userId);
        return optUser.orElse(null);
    }
}
