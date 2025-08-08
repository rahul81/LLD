package StateDesign.Good;

public class RedState implements TrafficLightState {

    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Change from RED to GREEN");
        context.setState(new GreenState());
    }

    @Override
    public String getColor() {
        return "RED";
    }
}
