package com.pw.cinema.seat_type;

import com.pw.cinema.theater.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SeatTypeService {
    @Autowired
    SeatTypeRepository seatTypeRepository;

    public Object createSeatType(SeatType seatType) {
        SeatType savedSeatType = seatTypeRepository.save(seatType);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedSeatType);
        resp.put("message", "Successful create theater");
        return resp;
    }
}
