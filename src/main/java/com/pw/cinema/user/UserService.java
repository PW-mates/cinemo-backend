package com.pw.cinema.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found!");
            throw new UsernameNotFoundException("User not found!");
        } else {
            log.info("User found: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public Object getUsers() {
        log.info("Fetching all users");
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", userRepository.findAll());
        resp.put("message", "Successful fetching data");
        return resp;
    }

    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    public User saveUser(User newUser) {
//        User findUserByUsername = userRepository.findByUsername(newUser.getUsername());
//        if (findUserByUsername == null)
//            throw new IllegalStateException("Username is already taken!");
        log.info("Saving new user to database {}", newUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public String getUsernameByJWT(HttpHeaders authorizationHeader) {
        String token = Objects.requireNonNull(authorizationHeader.getFirst("authorization")).substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET").getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public Object getUserByJWT(HttpHeaders authorizationHeader) {
        String username = getUsernameByJWT(authorizationHeader);
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Username not found");
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", user);
        resp.put("message", "Successful fetching data");
        return resp;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public Object updateUser(User user) {
        return userRepository.save(user);
    }
}
