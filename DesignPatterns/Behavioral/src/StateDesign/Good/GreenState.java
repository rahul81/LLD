package StateDesign.Good;

public class GreenState implements TrafficLightState {

    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Change from GREEN to YELLOW");
        context.setState(new YellowState());
    }

    @Override
    public String getColor() {
        return "GREEN";
    }

}
