package StateDesign.Good;

public interface TrafficLightState {
    void next(TrafficLightContext context);
    String getColor();
}
