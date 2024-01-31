package com.ktoufiquee.taskmanager.controller;

import com.ktoufiquee.taskmanager.model.Task;
import com.ktoufiquee.taskmanager.model.User;
import com.ktoufiquee.taskmanager.repository.UserRepository;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Set;

@RestController
public class UserController {

    final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        return repository.findById(username).get();
    }

    @PostMapping("/user/login")
    public boolean login(@RequestBody Map<String, String> body) {
        return repository.findById(body.get("username")).get().getPassword().equals(body.get("password"));
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        repository.save(user);
        return user;
    }

}

