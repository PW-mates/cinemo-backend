package com.pw.cinema.room;

import com.pw.cinema.theater.TheaterDtoLight;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomLightDto {
    @Id
    Long id;
    @NotNull
    String name;
    @NotNull
    int numberOfRows;
    @NotNull
    int seatsPerRow;
    @NotNull
    int seatsCount;
}
