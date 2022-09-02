import api.AdminResource;
import api.HotelResource;
import model.IRoom;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {
    public int UserOptions;
    static HotelResource hotelResource = HotelResource.getInstance();
    static AdminResource adminResource = AdminResource.getInstance();
    
    static IRoom room;
    
    private static final String emailPattern = "^(.+)@(.+).(.+)$";
    private static final Pattern pattern = Pattern.compile(emailPattern);

    static void displayMenu(){
        System.out.println("-----------Main Menu-----------");
        System.out.println("1. Find and reserve room");
        System.out.println("2. See my reservation");
        System.out.println("3. Create account");
        System.out.println("4. Admin menu");
        System.out.println("5. Exit");
    }

    public int getUserOptions(){
        return UserOptions;
    }

    public void setUserOptions(int UserOptions){
        this.UserOptions = UserOptions;
    }

    public final static void UserOptionsExecution(int UserOptions){
        try{
            switch(UserOptions){
                case 1:
                    reserveRoom();
                    break;
                case 2:
                    seeReservation();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    adminMenu();
                    break;
                case 5:
                    System.out.println("Hotel reservation system is exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void adminMenu() {
        boolean running = true;
        while(running){
            try{
                AdminMenu.displayAdminMenu();
                Scanner scanner = new Scanner(System.in);
                int adminOption = scanner.nextInt();
                AdminMenu.adminExecution(adminOption);
            } catch (Exception e){
                System.out.println("Invalid input");
            }
        }
    }

    private static void createAccount() {
        Scanner scanner;
        String email, firstName, lastName;
        try{
            scanner = new Scanner(System.in);
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            System.out.println("Enter first name: ");
            firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            lastName = scanner.nextLine();
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void seeReservation() {
        Scanner scanner;
        String email;
        try{
            scanner = new Scanner(System.in);
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            if (validateEmail(email).equals(email)){
                hotelResource.getCustomersReservation(email);
            } else {
                System.out.println("Invalid email");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String validateEmail(String email) {
        if (email == null) {
            return null;
        }
        if (!pattern.matcher(email).matches()) {
            return null;
        }
        return email;
    }

    public static Date getDate(String date, boolean flag) {
        Date checkDate = new Date();
        do {
            try{
                checkDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                flag = true;
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        } while (!flag);
        return checkDate;
    } 
    public static void reserveRoom(){
        boolean checkInOk = false;
        boolean checkOutOk = false;
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter check in date (yyyy-MM-dd): ");
        String checkin = scanner.nextLine();
        checkInDate = getDate(checkin, checkInOk);

        System.out.println("Enter check out date (yyyy-MM-dd): "); 
        String checkout = scanner.nextLine();
        checkOutDate = getDate(checkout, checkOutOk);
        
        if (checkInDate.before(checkOutDate) && checkOutDate.after(checkInDate)) {
            List<IRoom> roomlist = (List<IRoom>) hotelResource.findARoom(checkInDate, checkOutDate);

            if (!roomlist.isEmpty()){
                System.out.println("Available rooms:");
                for (IRoom room : roomlist) {
                    System.out.println(room.getRoomNumber());
                }
                bookRoom(roomlist, checkInDate, checkOutDate);
                System.out.println("Room reserved successfully");
            } else {
                System.out.println("Find available rooms in alternative dates");
                checkInDate = setAlternativeDate(checkInDate);
                checkOutDate = setAlternativeDate(checkOutDate);

                System.out.println("Alternate Available rooms:");
                roomlist = (List<IRoom>) hotelResource.findARoom(checkInDate, checkOutDate);

                if (!roomlist.isEmpty()){
                    for (IRoom room : roomlist) {
                        System.out.println(room.getRoomNumber());
                    }
                    bookRoom(roomlist, checkInDate, checkOutDate);
                    System.out.println("Room reserved successfully");
                } else {
                    System.out.println("No available rooms");
                }
            }
        } else {
            System.out.println("Check in date must be before check out date");
        }
    }

    private static Date setAlternativeDate(Date checkInDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }

    private static void bookRoom(List<IRoom> roomlist, Date checkInDate, Date checkOutDate) {
        boolean roomAvailable = false;

        for (IRoom room : roomlist) {
            System.out.println(room.toString());
        }

        String email = null;

        try {
            Scanner scanner = new Scanner(System.in);
            do {
                roomAvailable = false;
                System.out.println("Enter room number: ");
                String roomNumber = scanner.next();
                room = hotelResource.getRoom(roomNumber);

                for (IRoom room : roomlist) {
                    if (room.getRoomNumber().equals(roomNumber)) {
                        roomAvailable = true;
                    }
                }

                if (!roomAvailable) {
                    System.out.println("Room not available");
                } else {
                    do {
                        try {
                            System.out.println("Enter email: ");
                            email = scanner.next();
                            // if (hotelResource.getCustomer(email) != null) {
                            hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                            // break;
                            // } else {
                            // System.out.println("email not found");
                            // }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                            scanner.next();
                        }
                    } while (email == null);
                }
            } while (room == null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
