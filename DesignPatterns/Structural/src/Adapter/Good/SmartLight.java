package Structural.src.Adapter.Good;

public class SmartLight {
    
    public void connectViaWifi(){
        System.out.println("Connecting to Wifi");
    }

    public void swithcOn(){
        System.out.println("Smart light switching on");
    }

    public void switchOff(){
        System.out.println("Smart light switching off");
    }

    public void disconnectWifi(){
        System.out.println("Disconnecting Wifi");
    }
}
