package com.pw.cinema.theater;

import com.pw.cinema.role.Role;
import com.pw.cinema.room.RoomRepository;
import com.pw.cinema.user.User;
import com.pw.cinema.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
class TheaterServiceTest {
    @Autowired
    private TheaterService theaterService;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoomRepository roomRepository;

//    @BeforeEach
//    void setUp() {
//        theaterService = new TheaterService(theaterRepository,
//                userRepository, modelMapper, roomRepository);
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
//    }

    @Test
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
        theaterService.createTheater(theater);
    }

    @Test
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
    void updateTheater() {
    }

    @Test
    void getTheater() {
    }

    @Test
    void deleteTheater() {
    }
}