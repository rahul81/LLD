package Open_Closed.GoodCode;

import Open_Closed.GoodCode.Shapes.Circle;
import Open_Closed.GoodCode.Shapes.Triangle;

public class Main {
    
    public static void main(String[] args) {
        Circle c = new Circle(4.0);
        Triangle t = new Triangle(5.0, 7.0);

        System.out.println("Area of given Circle is : "+ c.calculateArea());
        System.out.println("Area of given Triangle is : "+ t.calculateArea());
    }
}

/*
 *  In the corrected code example, each shape class has a single responsibility:

• Base Class (Shape): This is an interface  with an abstract function calculateArea(). It defines a common interface for all shapes.

• Circle Class: This class implements the calculateArea() logic for circles.

• Triangle Class: This class implements the calculateArea() logic for triangles.
 * 
 */
