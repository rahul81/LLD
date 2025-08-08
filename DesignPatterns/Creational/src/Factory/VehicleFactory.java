package Factory;

public class VehicleFactory {

    
        public static Vehicle createVehicle(String vehicleType){
    
            switch (vehicleType) {
                case "Car":
                    return new Car();
                case "Bus":
                    return new Bus();
        
            default:
                throw new IllegalArgumentException("Unkown vehicle type : " + vehicleType);
        }

    }

}
