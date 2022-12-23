package com.pw.cinema.theater;

import com.pw.cinema.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @NotNull
    User manager;
}
