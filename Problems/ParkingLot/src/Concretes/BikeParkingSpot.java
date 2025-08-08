package Concretes;

import Abstracts.ParkingSpot;
import Abstracts.Vehicle;

public class BikeParkingSpot extends ParkingSpot {

    public BikeParkingSpot(int spotNumber) {
        super(spotNumber, "Bike");
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {
        return getSpotType().equalsIgnoreCase(vehicle.getVehicleType());
    }

}
