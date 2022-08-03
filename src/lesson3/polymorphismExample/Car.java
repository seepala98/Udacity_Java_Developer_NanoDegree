package lesson3.polymorphismExample;

public class Car extends Vehicle {
    public Car(){
        super("Car Start", "Car Stop", " Car Speed", " Car Direction");
    }

    @Override
    public void speed() {
        System.out.println("Car Speed");
    }
}