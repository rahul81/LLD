package Concretes;

import interfaces.FeeStratergy;

public class BasicHourlyFee implements FeeStratergy {

    @Override
    public double calculateFee(String vehicleType, int duration) {

        switch (vehicleType) {
            case "Car":
                return 10 * duration;
            case "Bike":
                return 5 * duration;
            case "Truck":
                return 15 * duration;
            default:
                throw new IllegalArgumentException("Unkown vehicleType");
        }
    }
}
