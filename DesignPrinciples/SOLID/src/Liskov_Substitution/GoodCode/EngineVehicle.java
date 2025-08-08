package Liskov_Substitution.GoodCode;

public  abstract class EngineVehicle extends Vehicle {
    
    abstract void startEngine(); // Must implement by inheriting class 
    // since all child classes will be with Engines
    
}
