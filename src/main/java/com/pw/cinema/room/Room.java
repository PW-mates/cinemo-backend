package com.pw.cinema.room;

import com.pw.cinema.theater.Theater;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int numberOfRows;
    int seatsPerRow;
    int seatsCount;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    Theater theater;
}
