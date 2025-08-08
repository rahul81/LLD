package AbstractFactory;

public class Main {
    

    // Using Abstract factory method does not require change in Factory code
    // Just define a new factory for new class implementing the abstract Factory

    public static void main(String[] args) {
        
        VehicleFactory hondFactory = new HondaFactory();
        Vehicle honda = hondFactory.createVehicle();
        honda.start();
        honda.stop();

        VehicleFactory BMWFactory = new BMWFactory();
        Vehicle bmw = BMWFactory.createVehicle();
        bmw.start();
        bmw.stop();

    }
}
