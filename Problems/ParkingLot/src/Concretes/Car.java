package Concretes;

import Abstracts.Vehicle;
import interfaces.FeeStratergy;

public class Car extends Vehicle {

    public Car(String licencePlate, String vehicleType, FeeStratergy paymentFeeStratergy) {
        super(licencePlate, vehicleType, paymentFeeStratergy);
    }
    
}
