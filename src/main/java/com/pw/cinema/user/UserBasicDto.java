package com.pw.cinema.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDto {
    @Id
    Long id;
    String firstName;
    String lastName;
    String username;
    String profilePicture;
}
