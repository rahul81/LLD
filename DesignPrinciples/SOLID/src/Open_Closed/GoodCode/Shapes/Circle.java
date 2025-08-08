package Open_Closed.GoodCode.Shapes;

import Open_Closed.GoodCode.Shape;
import java.lang.Math;

public class Circle implements Shape {

    private double radius;

    public Circle(double r) {
        radius = r;
    }

    @Override
    public double calculateArea(){
        return Math.PI * radius * radius;
    }
    
}
