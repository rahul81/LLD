package Liskov_Substitution.GoodCode;

public class Boat extends EngineVehicle {
    
    @Override
    public void startEngine(){
        // Boat specific engine start logic.
        System.out.println("Boat Engine logic...");
    }
}
