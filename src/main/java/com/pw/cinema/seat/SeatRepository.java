package com.pw.cinema.seat;

import com.pw.cinema.room.Room;
import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByRoom(Room room);
    Boolean existsAllByIdIn(Set<Long> ids);
    Set<Seat> findAllByIdIn(Set<Long> ids);
}
