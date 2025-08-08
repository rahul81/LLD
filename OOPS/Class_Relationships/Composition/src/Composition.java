package Class_Relationships.Composition.src;

/*
 * Composition is a stronger form of aggregation with full ownership—if the whole is destroyed, the parts cannot exist independently. 
 * Think of a House and its Rooms: without the house, the rooms cease to exist.
 */

public class Composition {
    
    public static void main(String[] args) {

        House h = new House();

        h.showHouse();

    }
}
