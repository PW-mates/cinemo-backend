package com.pw.cinema.theater;

import com.pw.cinema.user.UserDtoLight;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TheaterDtoLight {
    @Id
    Long id;
    @NotNull
    String name;
    @NotNull
    String address;
    @NotNull
    String city;
    @NotNull
    String country;
    @NotNull
    float latitude;
    @NotNull
    float longitude;
    @NotNull
    String phone;
    @NotNull
    String email;
    @NotNull
    String website;
    @NotNull
    UserDtoLight manager;
}
