package model;

import java.util.Date;

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();

    boolean isAvailable(Date checkInDate, Date checkOutDate);
}
