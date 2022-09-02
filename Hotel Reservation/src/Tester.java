
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        boolean running = true;
        try(Scanner scanner = new Scanner(System.in)){
            while (running){
                try{
                    MainMenu.displayMenu();
                    int userOption = scanner.nextInt();
                    MainMenu.UserOptionsExecution(userOption);
                } catch (Exception e){
                    System.out.println("Invalid input");
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
