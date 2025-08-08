package Polymorphism.RunTime.MethodOverloading.src;

/*
 Runtime polymorphism occurs when the method to be executed is determined during runtime.
 It is achieved through method overriding and is closely tied to inheritance 
 */

public class Vehicle {
    
    void start(){
        System.out.println("Starting a Vehicle");
    }

    public static void main(String[] args) {
        Car c = new Car();
        Bike b = new Bike();
        Truck t = new Truck();

        c.start();
        b.start();
        t.start();
    }
}
