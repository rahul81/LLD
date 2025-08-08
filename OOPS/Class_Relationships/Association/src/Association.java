package Class_Relationships.Association.src;

/*
 * Association is a general relationship where one class knows about or uses another.
 * It's like a friendship—two entities are aware of each other, but they exist independently
 */

public class Association {
    
    public static void main(String[] args) {
        Car c = new Car("BMW m5");
        Person p = new Person("Rahul", c);

        p.goForDrive();
    }
}
