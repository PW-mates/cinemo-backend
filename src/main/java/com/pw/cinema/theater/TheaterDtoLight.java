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
    String name;
    String address;
    String city;
    String country;
    float latitude;
    float longitude;
    String phone;
    String email;
    String website;
    UserDtoLight manager;
}
