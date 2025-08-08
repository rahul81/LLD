package Structural.src.Adapter.Good;

public class SmartLightAdapter implements SmartDevice {
    
    private SmartLight smartLight;

    SmartLightAdapter(SmartLight smartLight){
        this.smartLight = smartLight;
    }

    @Override
    public void swithcOn(){
        smartLight.connectViaWifi();
        smartLight.swithcOn();
    }

    @Override
    public void switchOff(){
        smartLight.switchOff();
        smartLight.disconnectWifi();
    }
}

