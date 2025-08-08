package Abstracts;

public abstract class ParkingSpot {

    private int spotNumber;
    private boolean isOccupied;
    private String spotType;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {

        if (isOccupied) {
            throw new IllegalStateException("Spot already occupied");
        }

        if (!canParkVehicle(vehicle)) {
            throw new IllegalArgumentException(
                    "This spot is not suitable for Vehicle type : " + vehicle.getVehicleType());
        }

        this.vehicle = vehicle;
        this.isOccupied = true;

    }

    public void vacate() {
        if (!isOccupied) {
            throw new IllegalStateException("Spot is already vacant");
        }

        this.vehicle = null;
        this.isOccupied = false;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getSpotType() {
        return spotType;
    }

}
