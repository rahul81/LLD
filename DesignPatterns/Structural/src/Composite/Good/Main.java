package Structural.src.Composite.Good;

public class Main {
 
    public static void main(String[] args) {
        
        SmartDevice airConditioner = new AirConditioner();
        SmartDevice smartLight = new SmartLight();

        // Create Composite component handler;
        CompositeComponent room1 = new CompositeComponent();
        room1.addComponent(airConditioner);
        room1.addComponent(smartLight);

        // Add more rooms, house, floor, zone // create new devices

        SmartDevice airConditioner2 = new AirConditioner();
        
        CompositeComponent room2 = new CompositeComponent();
        room2.addComponent(airConditioner2);


        room1.turnOn();
        room1.turnOff();
    }
}
