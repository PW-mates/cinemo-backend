package com.pw.cinema.screening;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScreeningDtoUpdate {
    Long openSale;
    Long date;
    Long movieId;
    Long roomId;
}
