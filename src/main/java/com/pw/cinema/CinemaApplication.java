package com.pw.cinema;

import com.pw.cinema.role.Role;
import com.pw.cinema.role.RoleService;
import com.pw.cinema.user.User;
import com.pw.cinema.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService) {
        return args -> {
            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
            roleService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveUser(new User("Jack",
                    "Daniel",
                    System.getenv("ADMIN_USERNAME"),
                    System.getenv("ADMIN_PASSWORD"),
                    new ArrayList<>()));

            userService.saveUser(new User("Jagger",
                    "Meister",
                    System.getenv("USER1_USERNAME"),
                    System.getenv("USER1_PASSWORD"),
                    new ArrayList<>()));

            userService.saveUser(new User("Tullamore",
                    "Dev",
                    System.getenv("USER2_USERNAME"),
                    System.getenv("USER2_PASSWORD"),
                    new ArrayList<>()));

            roleService.addRoleToUser(System.getenv("ADMIN_USERNAME"), "ROLE_ADMIN");
            roleService.addRoleToUser(System.getenv("USER1_USERNAME"), "ROLE_USER");
            roleService.addRoleToUser(System.getenv("USER2_USERNAME"), "ROLE_USER");
        };
    }
}
