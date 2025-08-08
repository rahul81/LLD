package AbstractFactory;

public class TotyotaFactory implements VehicleFactory {
    public Vehicle createVehicle(){
        return new Totyota();
    }
}
