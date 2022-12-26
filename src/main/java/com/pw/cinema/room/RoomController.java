package com.pw.cinema.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping(path="rooms")
    public ResponseEntity<Object> createRoom(@RequestBody Room room) {
        System.out.println("ok");
        return ResponseEntity.ok().body(roomService.createRoom(room));
    }
}
