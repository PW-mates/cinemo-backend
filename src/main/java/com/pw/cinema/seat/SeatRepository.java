package com.pw.cinema.seat;

import com.pw.cinema.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
//    @Query(value = "SELECT * FROM seat WHERE room_id = ?1", nativeQuery = true)
    List<Seat> findAllByRoom(Room room);
}
