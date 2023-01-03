package com.pw.cinema.config;

import com.pw.cinema.role.Role;
import com.pw.cinema.role.RoleService;
import com.pw.cinema.seat_type.SeatType;
import com.pw.cinema.seat_type.SeatTypeService;
import com.pw.cinema.user.User;
import com.pw.cinema.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, SeatTypeService seatTypeService) {
        return args -> {
            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
            roleService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveUser(new User("Jack",
                    "Daniel",
                    System.getenv("ADMIN_USERNAME"),
                    System.getenv("ADMIN_PASSWORD"),
                    new ArrayList<>()));

            userService.saveUser(new User("Jager",
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
            seatTypeService.createSeatType(new SeatType(1L, "Classic"));
            seatTypeService.createSeatType(new SeatType(2L, "VIP"));
        };
    }
}
