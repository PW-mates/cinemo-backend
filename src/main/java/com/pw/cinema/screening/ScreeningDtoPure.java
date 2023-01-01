package com.pw.cinema.screening;

import com.pw.cinema.movie.MovieDto;
import com.pw.cinema.room.RoomDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScreeningDtoPure {
    @NotNull
    Date openSale;
    @NotNull
    Date date;
    @NotNull
    Long movieId;
    @NotNull
    Long roomId;
}
