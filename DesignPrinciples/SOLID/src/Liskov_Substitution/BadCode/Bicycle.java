package Liskov_Substitution.BadCode;

public class Bicycle extends Vehicle {
    
    @Override
    public void startEngine(){
        // Bicycle has no engine 

        throw new UnsupportedOperationException("Bicycles don't have engines");
    }
}


/*
 * 
 * In this problematic approach, the Vehicle class has a startEngine() method, which is appropriate for Car but not for Bicycle. 
 * This violates the Liskov Substitution Principle (LSP) because the Bicycle class cannot be substituted / used in place of the Vehicle class without causing unexpected behavior (i.e., throwing an exception). 
 * When a subclass cannot fulfill the contract of its parent class, it leads to a breakdown in polymorphism, making the code less reliable and predictable. 
 * The Bicycle class, when forced to implement the startEngine() method, must either provide a meaningless implementation or throw an exception, both of which are undesirable outcomes.
 */
