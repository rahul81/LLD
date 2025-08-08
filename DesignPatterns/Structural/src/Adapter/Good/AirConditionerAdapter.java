package Structural.src.Adapter.Good;

public class AirConditionerAdapter implements SmartDevice {

    private AirConditioner airConditioner;

    public AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void swithcOn() {
        airConditioner.connectViaBluetooth();
        airConditioner.startCooling();
    }

    @Override
    public void switchOff() {
        airConditioner.stopCooling();
        airConditioner.disconnectBluetooth();

    }
}
