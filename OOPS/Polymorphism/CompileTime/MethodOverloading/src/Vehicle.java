package Polymorphism.CompileTime.MethodOverloading.src;

// Compile time polymorphism
// Same function name used can be called differently by changing args or type of args

public class Vehicle {
    

    void start(String vehicleType){
        System.out.println("Starting vehicle : " + vehicleType);
    }

    void start(String vehicleType, int speed){
        System.out.println("Starting vehicle : "+ vehicleType + " with inital speed "+ speed);
    }


    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        v.start("Car");
        v.start("Bike", 20);
    }

}
