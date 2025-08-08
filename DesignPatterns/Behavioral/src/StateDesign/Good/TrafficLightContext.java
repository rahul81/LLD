package StateDesign.Good;

public class TrafficLightContext {

    private TrafficLightState currentState;

    public TrafficLightContext(TrafficLightState state) {
        this.currentState = state;
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void next() {
        currentState.next(this);
    }

    public String getColor() {
        return currentState.getColor();
    }

}
