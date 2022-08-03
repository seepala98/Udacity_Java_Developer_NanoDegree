package lesson3.polymorphismExample;

public class Plane extends Vehicle {
    public Plane(){
        super("Plane Start", "Plane Stop", " Plane Speed", " Plane Direction");
    }

    @Override
    public void speed() {
        System.out.println("Plane Speed");
    }
}