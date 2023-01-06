package com.pw.cinema.theater;

import com.pw.cinema.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TheaterDto {
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
    UserDto manager;
}
