package Class_Relationships.Association.src;

public class Car {
    String model;

    Car(String model){
        this.model = model;
    }
    
    void drive(){
        System.out.println("Driving a " + model);
    }
}
