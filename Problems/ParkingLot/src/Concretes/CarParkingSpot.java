package Concretes;

import Abstracts.ParkingSpot;
import Abstracts.Vehicle;

public class CarParkingSpot extends ParkingSpot {

    public CarParkingSpot(int spotNumber) {
        super(spotNumber, "Car");
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {

        // Spot type will be car

        return getSpotType().equalsIgnoreCase(vehicle.getVehicleType());

    }

}
