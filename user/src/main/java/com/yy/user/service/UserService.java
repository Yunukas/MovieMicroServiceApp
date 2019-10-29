package com.yy.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yy.user.entity.User;
import com.yy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @HystrixCommand
    public void addUser(User user){
        userRepository.save(user);
    }

    @HystrixCommand
    public User getUserWithId(Long userId){
        final Optional<User> optUser = userRepository.findById(userId);
        return optUser.orElse(null);
    }
}
