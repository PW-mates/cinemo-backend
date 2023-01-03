package com.pw.cinema.seat;

import com.pw.cinema.room.RoomLightDto;
import com.pw.cinema.seat_type.SeatType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeatDto {
    @Id
    Long id;
    @NotNull
    int row;
    @NotNull
    int column;
    RoomLightDto room;
    SeatType type;
}
