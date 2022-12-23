package com.pw.cinema.theater;

import com.pw.cinema.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    UserRepository userRepository;

    public Object createTheater(Theater theater) {
        if (!userRepository.existsById(theater.getManager().getId()))
            throw new IllegalStateException("Manager doesn't exist");
        theater.setManager(userRepository.findUserById(theater.getManager().getId()));
        Theater savedTheater =  theaterRepository.save(theater);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedTheater);
        resp.put("message", "Successful fetching data");
        return resp;
    }
}
