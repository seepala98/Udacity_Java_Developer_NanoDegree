import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    private int adminOptions;
    static AdminResource adminResource = AdminResource.getInstance();
    static List<IRoom> newRoom;

    public static void displayAdminMenu(){
        System.out.println("-----------Admin Menu-----------");
        System.out.println("1. Display all customers");
        System.out.println("2. Display all rooms");
        System.out.println("3. Display all reservations");
        System.out.println("4. Add room");
        System.out.println("5. Back to main menu");
    }

    public int getAdminOptions(){
        return adminOptions;
    }

    public void setAdminOptions(int adminOptions){
        this.adminOptions = adminOptions;
    }

    public final static void adminExecution(int adminOptions){
        switch(adminOptions){
            case 1:
                displayAllCustomers();
                break;
            case 2:
                displayAllRooms();
                break;
            case 3:
                displayAllReservations();
                break;
            case 4:
                addRoom();
                break;
            case 5:
                backToDisplayMenu();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    private static void backToDisplayMenu() {
        boolean running = true;
        while(running){
            try{
                MainMenu.displayMenu();
                int userOption = new Scanner(System.in).nextInt();
                MainMenu.UserOptionsExecution(userOption);
            } catch (Exception e){
                System.out.println("Invalid input");
            }
        }
    }

    private static void displayAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()){
            System.out.println("No customers found");
        } else {
            System.out.println("Customers:");
            customers.forEach(customer -> System.out.println(customer.toString()));
        }
    }

    private static void displayAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()){
            System.out.println("No rooms found");
        } else {
            System.out.println("Rooms:");
            rooms.forEach(room -> System.out.println(room.toString()));
        }
    }

    private static void displayAllReservations() {
        if (adminResource.getAllRooms().isEmpty()){
            System.out.println("No reservations found");
        } else {
            adminResource.displayAllReservations();
        }
    }

    private static void addRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Add Room-----------");
        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.println("Enter room type: ");
        int roomType = scanner.nextInt();
        System.out.println("Enter room price: ");
        double roomPrice = scanner.nextDouble();
        newRoom = new ArrayList<>();
        Room room = new Room(String.valueOf(roomNumber), roomPrice, roomType==1?RoomType.SINGLE:RoomType.DOUBLE);
        newRoom.add(room);
        adminResource.addRoom(newRoom);
    }
}
