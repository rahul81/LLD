package Concretes;

import java.util.List;

import Abstracts.ParkingSpot;
import Abstracts.Vehicle;

public class ParkingLot {

    private List<ParkingSpot> parkingSpots;
    private static ParkingLot instance;

    private ParkingLot(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    // Singleton pattern to make sure there is only one parking lot 
    public static ParkingLot getParkingLotInstance(List<ParkingSpot> parkingSpots) {

        if (instance == null) {
            instance = new ParkingLot(parkingSpots);
        }

        return instance;

    }

    private ParkingSpot findParkingSpot(String vehicleType) {

        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied() && spot.getSpotType().equalsIgnoreCase(vehicleType)) {
                return spot;
            }
        }

        return null;

    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {

        ParkingSpot spot = findParkingSpot(vehicle.getVehicleType());

        if (spot != null) {
            spot.parkVehicle(vehicle);
            System.out.println("Vehicle parked successfully !");
            return spot;
        } else {
            System.out.println("No parking spots available .");
            return null;
        }

    }

    public void vacateSpot(ParkingSpot parkingSpot) {

        if (parkingSpot != null && parkingSpot.isOccupied()) {
            parkingSpot.vacate();
            System.out.println("Parking spot is vacated.");
            System.out.println("SpotNumber : " + parkingSpot.getSpotNumber());
        } else {
            System.out.println("Invalid Operation Spot is vacant.");
        }
    }

    public ParkingSpot getSpotByNumber(int number) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotNumber() == number) {
                return spot;
            }
        }
        return null;
    }

}
