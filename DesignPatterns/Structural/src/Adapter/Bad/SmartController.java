package Structural.src.Adapter.Bad;

public class SmartController {

    private String deviceType;

    public SmartController(String type) {
        deviceType = type;
    }

    public static void main(String[] args) {

        SmartController controller = new SmartController("AirConditioner");

        if (controller.deviceType == "AirConditioner") {
            AirConditoner airConditoner = new AirConditoner();
            airConditoner.connectViaBluetooth();
            airConditoner.turnOn();
        } else if (controller.deviceType == "SmartLight") {
            SmartLight smartLight = new SmartLight();
            smartLight.turnOn();
        }
    }
}

/*
 * 2 or more different classed with different methods to connect using different mediums.
 * Adapter pattern solves this problem by acting like a bridge between 2 or more different interfaces
 * 
*/
