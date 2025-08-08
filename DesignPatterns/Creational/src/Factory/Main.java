package Factory;

public class Main {

    public static void main(String[] args) {

        Vehicle car = VehicleFactory.createVehicle("Car");
        Vehicle bus = VehicleFactory.createVehicle("Bus");

        car.start();
        car.stop();

        bus.start();
        bus.stop();
    }

}
