package com.pw.cinema.room;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    String name;
    int rowsNumber;
    int seatInRowNumber;

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", rowsNumber=" + rowsNumber +
                ", seatInRowNumber=" + seatInRowNumber +
                '}';
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public int getSeatInRowNumber() {
        return seatInRowNumber;
    }

    public void setSeatInRowNumber(int seatInRowNumber) {
        this.seatInRowNumber = seatInRowNumber;
    }

    public Room(String name, int rowsNumber, int seatInRowNumber) {
        this.name = name;
        this.rowsNumber = rowsNumber;
        this.seatInRowNumber = seatInRowNumber;
    }

    public Room(Long roomId, String name, int rowsNumber, int seatInRowNumber) {
        this.roomId = roomId;
        this.name = name;
        this.rowsNumber = rowsNumber;
        this.seatInRowNumber = seatInRowNumber;
    }

    public Room() {
    }
}
