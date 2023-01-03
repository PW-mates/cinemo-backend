package com.pw.cinema.room;

import com.pw.cinema.theater.TheaterRepository;
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
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ModelMapper modelMapper;

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

    public RoomDto convertEntityToDto(Room room) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(room, RoomDto.class);
    }

    public Object getRooms() {
        List<RoomDto> savedRooms = roomRepository.findAll()
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedRooms);
        resp.put("message", "Successful fetching room data");
        return resp;
    }

    public Object getRoom(Long id) {
        Room savedRoom = roomRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Room with id not found")
        );
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", convertEntityToDto(savedRoom));
        resp.put("message", "Successful fetching room data");
        return resp;
    }

    public Object deleteRoom(Long id) {
        if (!roomRepository.existsById(id))
            throw new IllegalStateException("Room with id doesn't exist");
        roomRepository.deleteById(id);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Successful delete room");
        return resp;
    }

    public Object updateRoom(Long id, Room room) {
        if (!roomRepository.existsById(id))
            throw new IllegalStateException("Room with id doesn't exist");
        room.setId(id);
        Room savedRoom = roomRepository.save(room);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", convertEntityToDto(savedRoom));
        resp.put("message", "Successful delete room");
        return resp;
    }
}
