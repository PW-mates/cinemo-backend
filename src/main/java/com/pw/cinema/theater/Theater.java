package com.pw.cinema.theater;

import com.pw.cinema.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "manager_id")
    User manager;
}
