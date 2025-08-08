package Liskov_Substitution.GoodCode;

public class Car extends EngineVehicle{
    
    @Override
    public void startEngine(){
        // Engine starting logic for Car
        System.out.println("Car Enginer logic...");
    }
}
