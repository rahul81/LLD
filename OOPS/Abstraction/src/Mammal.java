package Abstraction.src;


public interface Mammal {

    void walk();

    default void getAnimalType() {
         System.out.println("This Animal is a mammal.");
    }
}
