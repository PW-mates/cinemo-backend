package com.pw.cinema.room;

import com.pw.cinema.theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByTheater(Theater theater);
}
