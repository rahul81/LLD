package Concretes;

import interfaces.FeeStratergy;

public class PremiumHourlyFee implements FeeStratergy {

    @Override
    public double calculateFee(String vehicleType, int duration) {
        switch (vehicleType) {
            case "Car":
                return 15 * duration;
            case "Bike":
                return 10 * duration;
            case "Truck":
                return 20 * duration;
            default:
                throw new IllegalArgumentException("Unkown vehicleType");
        }
    }
}
