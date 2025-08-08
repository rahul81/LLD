package StateDesign.Bad;


public class TrafficLight {
    private String color;

    public TrafficLight() {
        this.color = "RED";
    }

    public void next() {
        switch (color) {
            case "RED":
                System.out.println("Change to GREEN. Go !");
                color = "GREEN";
            case "GREEN":
                System.out.println("Change to YELLOW. Slow down!");
                color = "YELLOW";
            case "YELLOW":
                System.out.println("Change to RED. STOP !");
                color = "RED";
            default:
                break;
        }
    }

    public String getColor() {
        return color;
    }
}


/*
 * In this setup:

• The TrafficLight class manages its state using a simple color string.

• The next() method uses if-else statements to transition between states.

‍

Interviewer's Follow-Up Questions 🎤
Imagine you're in a job interview discussing this implementation. The interviewer might ask:

1. "What if we add a new state like BLINKING or MAINTENANCE mode?"

2. "How would you handle more complex transitions or behaviors based on time or external events?"

3. "Can you easily extend this system without modifying the existing TrafficLight class?"
 */