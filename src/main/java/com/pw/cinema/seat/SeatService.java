package com.pw.cinema.seat;

import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomDto;
import com.pw.cinema.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    RoomRepository roomRepository;

    public Object getSeatsByRoomId(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Room with id not found!"));
//        List<Seat> savedSeats = seatRepository.findAllByRoom(room);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
//        resp.put("data", savedSeats);
        resp.put("dataRoom", room);
        resp.put("message", "Successful fetching room data");
        return resp;
    }

    public Object getSeats() {
        List<Seat> savedSeats = seatRepository.findAll();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedSeats);
        resp.put("message", "Successful fetching room data");
        return resp;
    }

    public Object createSeat(Seat seat) {
        Seat savedSeat = seatRepository.save(seat);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedSeat);
        resp.put("message", "Successful fetching room data");
        return resp;
    }
}
