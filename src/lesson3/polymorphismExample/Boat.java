package lesson3.polymorphismExample;

public class Boat extends Vehicle {
    public Boat(){
        super("Boat Start", "Boat Stop", " Boat Speed", " Boat Direction");
    }

    @Override
    public void speed() {
        System.out.println("Boat Speed");
    }
}