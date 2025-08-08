package Structural.src.Adapter.Good;

public class SmartHomeController {
    
    public static void main(String[] args) {
        
        SmartDevice airConditioner = new AirConditionerAdapter(new AirConditioner());
        SmartDevice smartLight = new SmartLightAdapter(new SmartLight());

        airConditioner.swithcOn();
        airConditioner.switchOff();

        smartLight.swithcOn();
        smartLight.switchOff();
    }
}


/*
 * Now even if implementation for connecting for specific device changes, 
 * it does not require any change in Controller code.
 * 
*/
