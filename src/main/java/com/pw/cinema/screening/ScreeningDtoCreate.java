package com.pw.cinema.screening;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScreeningDtoCreate {
    Long openSale;
    @NotNull
    Long date;
    @NotNull
    Long movieId;
    @NotNull
    Long roomId;
}
