package com.pw.cinema.room;

import com.pw.cinema.theater.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TheaterRepository theaterRepository;

    public Object createRoom(Room room) {
        room.setTheater(theaterRepository
                .findById(room.getTheater().getId())
                .orElseThrow(()
                        -> new IllegalStateException("Theater doesn't exist")));
        Room savedRoom = roomRepository.save(room);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedRoom);
        resp.put("message", "Successful create room");
        return resp;
    }
}
