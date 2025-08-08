package AbstractFactory;

public class HondaFactory implements VehicleFactory{
    
    public Vehicle createVehicle(){
        return new Honda();
    }
}
