package com.pw.cinema.user;

import com.pw.cinema.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
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

    public User(String firstName, String lastName, String username, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
