package com.pw.cinema.user;


import com.pw.cinema.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long birthDate;
    String country;
    String gender;
    String bio;
    String personalWebsite;
    String language;
    Long createdAt;
    String email;
    Long updatedAt;
    String phone;
    String profilePicture;
    String status;
    Collection<Role> roles = new ArrayList<>();
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
