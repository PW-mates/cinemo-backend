package com.pw.cinema.screening;

import com.pw.cinema.movie.Movie;
import com.pw.cinema.room.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScreeningDto {
    Long id;
    Long openSale;
    Long date;
    Movie movie;
    RoomDto room;
}
