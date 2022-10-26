package com.pw.cinema.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path="/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path="/user")
    public void createUser(@RequestBody User newUser) {
        System.out.println(newUser);
        userService.createUser(newUser);
    }
}
