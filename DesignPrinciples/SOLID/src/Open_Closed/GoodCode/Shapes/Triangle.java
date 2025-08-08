package Open_Closed.GoodCode.Shapes;

import Open_Closed.GoodCode.Shape;

public class Triangle implements Shape{
    
    private double base;
    private double height;

    public Triangle(double b, double h){
        base = b;
        height = h;
    }

    @Override
    public double calculateArea(){
        return 0.5 * base * height;
    }
}
