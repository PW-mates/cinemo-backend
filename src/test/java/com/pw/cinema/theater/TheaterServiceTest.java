package com.pw.cinema.theater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TheaterServiceTest {

    @Mock
    private TheaterRepository theaterRepository;
    @Autowired
    private TheaterService theaterService ;

    @BeforeEach
    void setUp() {
        theaterService = new TheaterService(theaterRepository);
    }

    @Test
    void createTheater() {
//        User user = new User("Giang", "Do", "giangdo", "123123", );
//        Theater theater = new Theater(
//                1,
//                "Cine",
//                "Ludwika",
//                "Wars",
//                "PL",
//                1,
//                1,
//                "777",
//                "cine@gmail",
//                "cine.com",
//                user
//                );
//        theaterService.createTheater()
    }

    @Test
    void getTheaters() {
        Object resp = theaterService.getTheaters();
        Map<String, Object> expected = new HashMap<>();
        List<Theater> theaters = new ArrayList<>();
        expected.put("success", true);
        expected.put("data", theaters);
        expected.put("message", "Successful fetching data");
        assertThat(resp).isEqualTo(expected);
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