package com.pw.cinema.seat;

import com.pw.cinema.room.RoomLightDto;
import com.pw.cinema.seat_type.SeatType;
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
    int row;
    int column;
    RoomLightDto room;
    SeatType type;
}
