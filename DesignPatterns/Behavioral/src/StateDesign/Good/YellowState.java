package StateDesign.Good;

public class YellowState implements TrafficLightState{
    
    @Override
    public void next(TrafficLightContext context){
        System.out.println("Change from GREEN to YELLOW");
        context.setState(new RedState());
    }

    @Override
    public String getColor(){
        return "YELLOW";
    }
}
