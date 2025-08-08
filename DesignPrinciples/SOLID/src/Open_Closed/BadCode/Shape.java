package Open_Closed.BadCode;

/*
 * 🚪Open/Closed Principle
This principle states that Software entities (classes, modules, functions, etc.) should be open for extension, 
but closed for modification which means you should be able to extend a class behavior, without modifying it.

‍
🌟Why it matters:
• Prevents breaking existing code.
• Encourages reusable components.

‍

💭Example:
Suppose we have a Shape class that calculates the area of different shapes. 
Initially, it supports only circles and rectangles. Adding a new shape, like a triangle, would require modifying the existing code.

The Open/Closed Principle states that software entities should be open for extension but closed for modification. Here since we are modifying the existing code and not extending it, that is why the current approach is problematic:
 */

public class Shape {
    private String type;

    public double calculateArea() {
        if (type.equals("circle")) {
            // Circle area calculation
        } else if (type.equals("rectangle")) {
            // Rectangle area calculation
        }
        // Adding a triangle requires modifying this method

        return 0.0;
    }
}
