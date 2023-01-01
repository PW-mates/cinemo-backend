package com.pw.cinema.screening;


import com.pw.cinema.movie.Movie;
import com.pw.cinema.room.Room;
import com.pw.cinema.theater.Theater;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Movie movie;
    Room room;
    Theater theater;
    Date openSale;
    Date date;
}
