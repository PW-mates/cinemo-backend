package com.pw.cinema.room;

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
    String name;
    int numberOfRows;
    int seatsPerRow;
    int seatsCount;
}
