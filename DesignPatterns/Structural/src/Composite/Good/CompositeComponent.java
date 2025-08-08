package Structural.src.Composite.Good;

import java.util.ArrayList;

public class CompositeComponent implements SmartDevice {

    private ArrayList<SmartDevice> components = new ArrayList<>();

    public void addComponent(SmartDevice component) {
        components.add(component);
    }

    public void removeComponent(SmartDevice component) {
        components.remove(component);
    }

    @Override
    public void turnOn() {

        for (SmartDevice com : components) {
            com.turnOn();
        }

    }

    @Override
    public void turnOff() {
        for (SmartDevice com : components) {
            com.turnOn();
        }

    }

}
