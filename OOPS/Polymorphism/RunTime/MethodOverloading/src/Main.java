package Polymorphism.RunTime.MethodOverloading.src;

public class Main {
    
    public static void main(String[] args) {
        
        Vehicle[] vehicles = {new Car(), new Bike(), new Truck()};

        for (Vehicle v : vehicles){
            v.start(); // Runtime Determines which start method to call from which class.
        }
        
    }
}
