package com.pw.cinema.screening;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path="screenings/{id}")
    public ResponseEntity<Object> getScreening(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(screeningService.getScreening(id));
    }

    @PatchMapping(path="screenings/{id}")
    public ResponseEntity<Object> updateScreening(@PathVariable("id") Long id, @RequestBody ScreeningDtoPure screening) {
        return ResponseEntity.ok().body(screeningService.updateScreening(id, screening));
    }

    @DeleteMapping(path = "screenings/{id}")
    public ResponseEntity<Object> updateScreening(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(screeningService.deleteScreening(id));
    }
}
