package model;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domain.com");
        System.out.println("This is info of customeer with valid email: "+ customer);

        Customer customer_wrong_email = new Customer("first", "second", "email");
        System.out.println("This is the info where customer email is incorrect: " +customer_wrong_email);
    }
}
