package com.pw.cinema.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void createUser(User newUser) {
        Optional<User> findUserByUsername = userRepository.findByUsername(newUser.getUsername());
        if (findUserByUsername.isPresent())
            throw new IllegalStateException("Username is already taken!");
        userRepository.save(newUser);
    }
}
