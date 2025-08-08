package Abstracts;

import interfaces.FeeStratergy;

public abstract class Vehicle {

    private String licencePlate;
    private String vehicleType;
    private FeeStratergy paymentFeeStraterygy;

    public Vehicle(String licencePlate, String vehicleType, FeeStratergy paymentFeeStratergy) {
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.paymentFeeStraterygy = paymentFeeStratergy;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public double calculateFee(int duration) {
        return paymentFeeStraterygy.calculateFee(vehicleType, duration);
    }

}
