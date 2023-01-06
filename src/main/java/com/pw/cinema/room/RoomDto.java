package com.pw.cinema.room;

import com.pw.cinema.theater.TheaterDtoLight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    @Id
    Long id;
    String name;
    int numberOfRows;
    int seatsPerRow;
    int seatsCount;
    TheaterDtoLight theater;
}
