package com.pw.cinema.screening;

import com.pw.cinema.movie.Movie;
import com.pw.cinema.room.Room;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NotNull
    Movie movie;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotNull
    Room room;
    @NotNull
    Long openSale;
    @NotNull
    Long date;
}
