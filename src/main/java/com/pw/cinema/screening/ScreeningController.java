package com.pw.cinema.screening;

import com.pw.cinema.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScreeningController {
    ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @PostMapping(path="screenings")
    public ResponseEntity<Object> createScreening(@RequestBody ScreeningDtoPure screening) {
        return ResponseEntity.ok().body(screeningService.createScreening(screening));
    }
}
