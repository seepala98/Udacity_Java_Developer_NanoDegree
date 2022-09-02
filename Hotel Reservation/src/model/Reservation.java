package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public IRoom getRoom(){
        return this.room;
    }

    public Date getCheckInDate(){
        return this.checkInDate;
    }

    public Date getCheckOutDate(){
        return this.checkOutDate;
    }

    @Override
    public String toString(){
        return "Reservation{" + "customer=" + customer + ", room=" + room + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + '}';
    }

    public boolean isAvailable(Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(this.checkOutDate)|| checkOutDate.before(this.checkInDate)) {
            return true;
        }
        return false;
    }
}
