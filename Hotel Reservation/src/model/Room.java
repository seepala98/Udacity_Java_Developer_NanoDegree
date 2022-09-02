package model;

import java.util.Date;

public class Room implements IRoom {
    private String roomNumber;
    private Double roomPrice;
    private RoomType enumeration;
    private boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.enumeration = enumeration;
        this.isFree = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + ": " + roomPrice + " " + enumeration;
    }

    @Override
    public boolean isAvailable(Date checkInDate, Date checkOutDate) {
        return false;
    }
}
