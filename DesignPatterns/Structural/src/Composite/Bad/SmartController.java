package Structural.src.Composite.Bad;

public class SmartController {

    public static void main(String[] args) {

        AirConditoner airConditoner = new AirConditoner();
        SmartLight smartLight = new SmartLight();
        System.out.println("Turning On devices in room 1");
        airConditoner.turnOn();
        smartLight.turnOn();

        // Manually create new room and manage devices
        System.out.println("Turning on devices in room 2");
        airConditoner.turnOn();
        smartLight.turnOn();

        System.out.println("Turning off devices in room 1");
        airConditoner.turnOff();
        smartLight.turnOff();

    }

        
}

/*
 * 
 * In this traditional approach each room and its devices are needed to be managed mannually.
 * Composite pattern solves this problem.
*/
