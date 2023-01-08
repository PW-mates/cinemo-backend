package com.pw.cinema.theater;

import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomRepository;
import com.pw.cinema.user.User;
import com.pw.cinema.user.UserRepository;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TheaterServiceTest {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    private TheaterService theaterService;

    @Test
    @Order(1)
    void createTheater() {
        User user = new User(1L);
        Theater theater = new Theater(
                2L,
                "Cine",
                "Ludwika",
                "Wars",
                "PL",
                1,
                1,
                "777",
                "cine@gmail",
                "cine.com",
                user
        );
        try {
            theaterRepository.save(theater);
        } catch (Exception e) {
            System.out.println(e);
        }
        Object resp = theaterService.createTheater(theater);
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("data", theater);
        expected.put("message", "Successful create theater");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(2)
    void getTheaters() {
        Object resp = theaterService.getTheaters();
        Map<String, Object> expected = new HashMap<>();
        List<TheaterDto> theaters = theaterRepository
                .findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        expected.put("success", true);
        expected.put("data", theaters);
        expected.put("message", "Successful fetching data");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }


    private TheaterDto convertEntityToDto(Theater theater) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(theater, TheaterDto.class);
    }

    @Test
    @Order(3)
    void updateTheater() {
        User user = userRepository.findUserById(1L);
        Theater theater = new Theater(
                1L,
                "Cine",
                "Ludwika",
                "Wars",
                "PL",
                1,
                1,
                "777",
                "cine@gmail",
                "cine.com",
                user
        );
        Object resp = theaterService.updateTheater(1L, theater);
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("data", convertEntityToDto(theater));
        expected.put("message", "Successful update theater");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(4)
    void getTheater() {
        Object resp = theaterService.getTheater(1L);
//        Optional<Theater> theater = theaterRepository.findById(1L);
        Theater theater = theaterRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Theater with id doesn't exist"));
        List<Room> rooms = roomRepository.findAllByTheater(theater);
        Map<String, Object> expected = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("theater", theater);
        data.put("rooms", rooms);
        expected.put("success", true);
        expected.put("data", data);
        expected.put("message", "Successful fetching data");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(5)
    void deleteTheater() {
        Map<String, Object> expected = new HashMap<>();
        expected.put("success", true);
        expected.put("message", "Successful delete");
        Object resp = theaterService.deleteTheater(1L);
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }
}