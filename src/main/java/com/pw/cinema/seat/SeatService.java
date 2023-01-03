package com.pw.cinema.seat;

import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomRepository;
import com.pw.cinema.seat_type.SeatType;
import com.pw.cinema.seat_type.SeatTypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    @Autowired
    SeatTypeRepository seatTypeRepository;
    @Autowired
    ModelMapper modelMapper;

    public Object getSeatsByRoomId(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Room with id not found!"));
        List<SeatDto> savedSeats = seatRepository.findAllByRoom(room)
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedSeats);
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
        resp.put("data", convertEntityToDto(savedSeat));
        resp.put("message", "Successful fetching room data");
        return resp;
    }

    public void createSerialSeats(Room room) {
        int numbRow = room.getNumberOfRows();
        int numbCol = room.getSeatsPerRow();
        SeatType seatType = seatTypeRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("Seat type not found!"));
        for (int i = 1; i <= numbRow; i++)
            for (int j = 1; j <= numbCol; j++)
                seatRepository.save(new Seat(null, i, j, room, seatType));
    }

    public SeatDto convertEntityToDto(Seat seat) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(seat, SeatDto.class);
    }
}
