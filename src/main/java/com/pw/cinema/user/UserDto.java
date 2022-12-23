package com.pw.cinema.user;


import com.pw.cinema.theater.Theater;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
}
