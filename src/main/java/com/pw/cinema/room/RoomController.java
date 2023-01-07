package com.pw.cinema.room;

import com.pw.cinema.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    SeatService seatService;

    @PostMapping(path="rooms")
    public ResponseEntity<Object> createRoom(@RequestBody Room room) {
        return ResponseEntity.ok().body(roomService.createRoom(room));
    }

    @GetMapping(path = "rooms")
    public ResponseEntity<Object> getRooms() {
        return ResponseEntity.ok().body(roomService.getRooms());
    }

    @GetMapping(path = "rooms/{id}")
    public ResponseEntity<Object> getRoom(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(roomService.getRoom(id));
    }

    @DeleteMapping(path = "rooms/{id}")
    public ResponseEntity<Object> deleteRoom(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(roomService.deleteRoom(id));
    }

    @PatchMapping(path = "rooms/{id}")
    public ResponseEntity<Object> updateRoom(@PathVariable("id") Long id, @RequestBody Room room) {
        return ResponseEntity.ok().body(roomService.updateRoom(id, room));
    }

    @GetMapping(path = "rooms/{id}/seats")
    public ResponseEntity<Object> getSeats(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(seatService.getSeatsByRoomId(id));
    }
}
