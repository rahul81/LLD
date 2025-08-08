package Liskov_Substitution.GoodCode;

public abstract class Vehicle {
    
    // abstract void move(); // must implement by inheriting class

    // Common code that needs to be shared among child classes.
    void move() {
        // movement logic
        System.out.println("Vehicle is moving...");
    };
}
