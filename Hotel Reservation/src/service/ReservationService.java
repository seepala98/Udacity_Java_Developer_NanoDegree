package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Calendar;
import java.util.Collection;

public class ReservationService {

    private static final int RECOMMENED_ROOMS_DEFAULT_PLUS_DAYS = 7;

    private static ReservationService instance;

    private final Map<String, IRoom> rooms = new HashMap<>();
    private final static Map<String, Collection<Reservation>> reservations = new HashMap<>();

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    private ReservationService(){}

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (isAvailable(room, checkInDate, checkOutDate)) {
            return null;
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        Collection<Reservation> customerReservations = getCustomersReservation(customer);

        if(customerReservations == null){
            customerReservations = new LinkedList<>();
        }

        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);

        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return findAvailableRooms(checkInDate, checkOutDate);
    }

    private Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> allReservations = getAllReservedRooms(checkInDate, checkOutDate);
        Collection<IRoom> available = new LinkedList<>();
        for (IRoom room : getAllRooms()) {
            // if the reservation is in the same time period as the check in and check out date
            if(!allReservations.contains(room)) {
                available.add(room);
            }
        }
        return available;
    }

    public Collection<IRoom> findAlternativeRooms(Date checkInDate, Date checkOutDate){
        return findAvailableRooms(addDefaultPlusDays(checkInDate), addDefaultPlusDays(checkOutDate));
    }

    private Date addDefaultPlusDays(Date checkInDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, RECOMMENED_ROOMS_DEFAULT_PLUS_DAYS);
        return calendar.getTime();
    }

    public void printAllReservations(){
        for(Map.Entry<String, Collection<Reservation>> entry : reservations.entrySet()){
            System.out.println(entry.getKey());
            for(Reservation reservation : entry.getValue()){
                System.out.println(reservation.toString());
            }
        }
    }

    public static Collection<Reservation> getAllReservations() {
        Collection<Reservation> allReservations = new LinkedList<>();
        for(Collection<Reservation> reserve : reservations.values()){
            allReservations.addAll(reserve);
        }
        return allReservations;
    }

    private static Collection<IRoom> getAllReservedRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> allReservedRooms = new LinkedList<>();
        for(Reservation reservation : getAllReservations()){
                if(reservation.isAvailable(checkInDate, checkOutDate)){
                    allReservedRooms.add(reservation.getRoom());
                }
        }
        return allReservedRooms;
    }

    public static boolean isAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        Collection<IRoom> reservation = getAllReservedRooms(checkInDate, checkOutDate);
        if (reservation.contains(room)){
            return true;
        }
        return false;
    }
}
