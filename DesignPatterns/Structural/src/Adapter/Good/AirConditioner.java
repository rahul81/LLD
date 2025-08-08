package Structural.src.Adapter.Good;

public class AirConditioner {
    
    public void connectViaBluetooth(){
        System.out.println("Connect via bluetooth");
    }

    public void startCooling(){
        System.out.println("AC cooling");
    }

    public void stopCooling(){
        System.out.println("Stop cooling");
    }

    public void disconnectBluetooth(){
        System.out.println("Disconnecting bluetooth.");
    }
}
