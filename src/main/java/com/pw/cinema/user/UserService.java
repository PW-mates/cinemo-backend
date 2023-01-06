package com.pw.cinema.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username not found"));
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
        resp.put("data", userRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList()));
        resp.put("message", "Successful fetching data");
        return resp;
    }

    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username not found"));
    }

    public UserDto saveUser(User newUser) {
//        User findUserByUsername = userRepository.findByUsername(newUser.getUsername());
//        if (findUserByUsername == null)
//            throw new IllegalStateException("Username is already taken!");
        log.info("Saving new user to database {}", newUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return convertEntityToDto(userRepository.save(newUser));
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
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username not found"));
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", convertEntityToDto(user));
        resp.put("message", "Successful fetching data");
        return resp;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    private UserDto convertEntityToDto(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserDto.class);
    }

    public Object updatePassword(HttpHeaders header, ChangingPassword changingPassword) {
        String username = getUsernameByJWT(header);
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalStateException("Current userdata is not matched or not found!"));
        if (!passwordEncoder.matches(changingPassword.getOldPassword(), user.getPassword()))
            throw new IllegalStateException("Old password doesn't match");
        user.setPassword(passwordEncoder.encode(changingPassword.getNewPassword()));
        userRepository.save(user);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Successful update password");
        return resp;
    }

    private UserBasicDto convertEntityToBasicDto(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserBasicDto.class);
    }

    public Object getAccounts() {
        List<UserBasicDto> accounts = userRepository
                .findAll().stream()
                .map(this::convertEntityToBasicDto).toList();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", accounts);
        resp.put("message", "Successful fetching data");
        return resp;
    }
}
