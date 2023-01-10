package com.pw.cinema.seat_type;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class SeatTypeServiceTest {
    @Autowired
    private SeatTypeService seatTypeService;

    @Test
    void getSeatTypes() {
        Object resp = seatTypeService.getSeatTypes();
        Map<String, Object> expected = new HashMap<>();
        List<SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(new SeatType(1L, 15, "Classic", "3CB371"));
        seatTypes.add(new SeatType(2L, 30, "VIP", "ffb703"));
        expected.put("success", true);
        expected.put("data", seatTypes);
        expected.put("message", "Successful get seat type");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }
}