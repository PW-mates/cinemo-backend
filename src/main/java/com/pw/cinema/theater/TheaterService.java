package com.pw.cinema.theater;

import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomLightDto;
import com.pw.cinema.room.RoomRepository;
import com.pw.cinema.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoomRepository roomRepository;

    public Object createTheater(Theater theater) {
        if (!userRepository.existsById(theater.getManager().getId()))
            throw new IllegalStateException("Manager doesn't exist");
        theater.setManager(userRepository.findUserById(theater.getManager().getId()));
        Theater savedTheater = theaterRepository.save(theater);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", convertEntityToDto(savedTheater));
        resp.put("message", "Successful create theater");
        return resp;
    }

    private TheaterDto convertEntityToDto(Theater theater) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(theater, TheaterDto.class);
    }

    public Object getTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", theaters.stream().map(this::convertEntityToDto).collect(Collectors.toList()));
        resp.put("message", "Successful fetching data");
        return resp;
    }

    public Object updateTheater(Long id, Theater theater) {
        if (!theaterRepository.existsById(id))
            throw new IllegalStateException("Theater with id doesn't exist");
        theater.setId(id);
        Theater savedTheater = theaterRepository.save(theater);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", convertEntityToDto(savedTheater));
        resp.put("message", "Successful update theater");
        return resp;
    }

    private RoomLightDto convertRoomEntityToLightDto(Room room) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(room, RoomLightDto.class);
    }

    public Object getTheater(Long id) {
        Theater theater = theaterRepository.findById(id).orElseThrow(() -> new IllegalStateException("Theater with id doesn't exist"));
        List<RoomLightDto> rooms = roomRepository.findAllByTheater(theater)
                .stream().map(this::convertRoomEntityToLightDto)
                .collect(Collectors.toList());
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("theater", convertEntityToDto(theater));
        data.put("rooms", rooms);
        resp.put("success", true);
        resp.put("data", data);
        resp.put("message", "Successful fetching data");
        return resp;
    }

    public Object deleteTheater(Long id) {
        Theater theater = theaterRepository.findById(id).orElseThrow(() -> new IllegalStateException("Theater with id doesn't exist"));
        theaterRepository.deleteById(id);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Successful delete");
        return resp;
    }
}
