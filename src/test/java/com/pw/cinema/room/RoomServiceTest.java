package com.pw.cinema.room;

import com.pw.cinema.theater.Theater;
import com.pw.cinema.theater.TheaterDtoLight;
import com.pw.cinema.theater.TheaterRepository;
import com.pw.cinema.theater.TheaterService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomServiceTest {

    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ModelMapper modelMapper;
    private TheaterDtoLight converToDtoLight(Theater theater) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(theater, TheaterDtoLight.class);
    }

    @Test
    @Order(1)
    void getRooms() {
        Object resp =  roomService.getRooms();
        List<RoomDto> rooms = new ArrayList<>();
        TheaterDtoLight theater = converToDtoLight(theaterRepository.findById(1L).orElseThrow(() -> new RuntimeException("Theater not found")));
        rooms.add(new RoomDto(1L, "Room 1", 5, 5, 25, theater));
        rooms.add(new RoomDto(2L, "Room 2", 5, 5, 25, theater));
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("data", rooms);
        expected.put("message", "Successful fetching room data");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(2)
    void getRoom() {
        Object resp =  roomService.getRoom(2L);
        TheaterDtoLight theater = converToDtoLight(theaterRepository.findById(1L).orElseThrow(() -> new RuntimeException("Theater not found")));
        RoomDto room = new RoomDto(2L, "Room 2", 5, 5, 25, theater);
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("data", room);
        expected.put("message", "Successful fetching room data");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(3)
    void deleteRoom() {
        Object resp =  roomService.deleteRoom(2L);
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("message", "Successful delete room");
        Optional<Room> room = roomRepository.findById(2L);
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
        assertFalse(room.isPresent());
    }

    @Test
    @Order(4)
    void updateRoom() {
        Theater theater = theaterRepository.findById(1L).orElseThrow(() -> new RuntimeException("Theater not found"));
        Room room = new Room(1L, "Room 111", 5, 5, 25, theater);
        Object resp =  roomService.updateRoom(1L, room);
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("message", "Successful update room");
        expected.put("data", roomService.convertEntityToDto(room));
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }
}