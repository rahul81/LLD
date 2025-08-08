package StateDesign.Good;

public class Main {

    public static void main(String[] args) {

        // Initiallise with a Red State
        TrafficLightContext trafficlight = new TrafficLightContext(new RedState());
        System.out.println("Traffic light is : " + trafficlight.getColor());
        trafficlight.next();
        trafficlight.next();
        trafficlight.next();

        // Using state classes it becomes easy to add new STATES without changing
        // Trafficlight Context or Main code
        // Just need to create new STATE classes and link them
    }

}
