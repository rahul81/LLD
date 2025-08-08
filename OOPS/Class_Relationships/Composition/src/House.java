package Class_Relationships.Composition.src;

public class House {
    Room livingRoom;
    Room kitchen;

    House(){
        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");
    }


    void showHouse() {
        System.out.println("House Contains : "+ livingRoom.name + " and " + kitchen.name);
    }
}
