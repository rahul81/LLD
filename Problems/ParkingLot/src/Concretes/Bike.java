package Concretes;

import Abstracts.Vehicle;
import interfaces.FeeStratergy;

public class Bike extends Vehicle{

    public Bike(String licencePlate, String vehicleType, FeeStratergy paymentFeeStratergy) {
        super(licencePlate, vehicleType, paymentFeeStratergy);
    }
    
}
