package com.pw.cinema.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/manage/users")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(path = "/account/user/save")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @GetMapping(path = "/account/info")
    public ResponseEntity<Object> getUser(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok().body(userService.getUserByJWT(headers));
    }

    @PatchMapping("/account/info")
    public ResponseEntity<Object> updateUser(@RequestHeader HttpHeaders headers, @RequestBody User user) {
        Map<String, Object> resp = new HashMap<>();
        System.out.println(user);
        User inDB = userService.findUserById(user.getId());
        if (inDB != null) user.setPassword(inDB.getPassword());
        if (inDB == null || !Objects.equals(user.getUsername(), userService.getUsernameByJWT(headers))) {
            resp.put("success", false);
            resp.put("message", "Current userdata is not matched or not found!");
            return new ResponseEntity<>(
                    resp, HttpStatus.BAD_REQUEST
            );
        }
        userService.updateUser(user);
        resp.put("success", true);
        resp.put("message", "Successful update user!");
        resp.put("data", user);
        return new ResponseEntity<>(
                resp, HttpStatus.OK);
    }

    @PostMapping(path = "/account/password")
    public ResponseEntity<Object> updatePassword(@RequestHeader HttpHeaders header, @RequestBody ChangingPassword changingPassword) {
        return ResponseEntity.ok().body(userService.updatePassword(header, changingPassword));
    }
}

@Data
class ChangingPassword {
    private String oldPassword;
    private String newPassword;
}