package Liskov_Substitution.GoodCode;

public class Main {
    
    public static void main(String[] args) {
        
        // Parent    //Child
        EngineVehicle car = new Car();
        EngineVehicle boat = new Boat();

        car.startEngine();
        car.move();

        boat.startEngine();
        boat.move();

        // non engine
        NonEngineVehicle cycle = new Bicycle();
        cycle.move();



    }
}

/*
 *
 * 🔄 Liskov Substitution Principle
The principle was introduced by Barbara Liskov in 1987 and according to this principle Derived or child classes must be substitutable for their base or parent classes. 
This principle ensures that any class that is the child of a parent class should be usable in place of its parent without any unexpected behavior. 

* In the corrected code example, the vehicle hierarchy is refined to distinguish between motorized and non-motorized vehicles:

• Base Class (Vehicle): This is an abstract base class with common vehicle behaviors.

• EngineVehicle Class: This abstract class extends Vehicle and includes engine-related methods.

• NonEngineVehicle Class: This abstract class extends Vehicle and excludes engine-related methods.

• Car Class: This class extends EngineVehicle and implements the startEngine method.

• Bicycle Class: This class extends NonEngineVehicle and does not need to implement engine-related methods.


💥This design ensures that:
1. Each subtype fully satisfies the behavioral contract of its parent type

2. Client code can interact with either vehicle type without unexpected behavior
 */
