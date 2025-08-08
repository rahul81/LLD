import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Abstracts.ParkingSpot;
import Abstracts.Vehicle;
import Concretes.BasicHourlyFee;
import Concretes.BikeParkingSpot;
import Concretes.Car;
import Concretes.CarParkingSpot;
import Concretes.CreditCardPayment;
import Concretes.ParkingLot;
// import Concretes.PremiumHourlyFee;
import Concretes.UPIPayment;
import interfaces.PaymentStratergy;

public class Main {

    public static void main(String[] args) {

        List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();

        ParkingSpot CarSpot1 = new CarParkingSpot(1);
        ParkingSpot CarSpot2 = new CarParkingSpot(2);
        ParkingSpot BikeSpot1 = new BikeParkingSpot(3);
        ParkingSpot BikeSpot2 = new BikeParkingSpot(4);

        parkingSpots.add(CarSpot1);
        parkingSpots.add(CarSpot2);
        parkingSpots.add(BikeSpot1);
        parkingSpots.add(BikeSpot2);

        ParkingLot parkingLot = ParkingLot.getParkingLotInstance(parkingSpots);

        // Fee Stratergies
        BasicHourlyFee basicHourlyFee = new BasicHourlyFee();
        // PremiumHourlyFee premiumHourlyFee = new PremiumHourlyFee();

        Vehicle car = new Car("MH01ASDF", "Car", basicHourlyFee);
        Vehicle bike1 = new Car("MH01ARTY", "Bike", basicHourlyFee);

        // Park vehicle

        ParkingSpot parkedCar1Spot = parkingLot.parkVehicle(car);
        ParkingSpot parkedBike1Spot = parkingLot.parkVehicle(bike1);


        // Can refactor below code in functions for payment and vacating

        // payment method
        // For car spot
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select payment method for your vehicle:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        int paymentMethod = scanner.nextInt();

        if (parkedCar1Spot != null) {
            // Calculate fee based on duration
            double fee = car.calculateFee(4);
            getPaymentStratergy(paymentMethod).processPayment(fee);
            parkingLot.vacateSpot(parkedCar1Spot);

        }
        scanner.nextLine(); // clear buffer

        // For Bike
        System.out.println("Select payment method for your vehicle:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        paymentMethod = scanner.nextInt();

        if (parkedBike1Spot != null) {
            // Calculate fee based on duration
            double fee = bike1.calculateFee(2);
            getPaymentStratergy(paymentMethod).processPayment(fee);
            parkingLot.vacateSpot(parkedBike1Spot);

        }

        scanner.close();

    }

    public static PaymentStratergy getPaymentStratergy(int num) {
        switch (num) {
            case 1:
                return new CreditCardPayment();
            case 2:
                return new UPIPayment();
            default:
                throw new IllegalArgumentException("Unkown payment method selected.");
        }
    }
}
