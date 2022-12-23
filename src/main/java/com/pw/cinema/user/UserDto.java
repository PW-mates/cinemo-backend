package com.pw.cinema.user;


import com.pw.cinema.role.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
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
}
