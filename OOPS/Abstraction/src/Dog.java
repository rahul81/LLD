package Abstraction.src;

public class Dog extends Animal implements Mammal, Pet {

    
    public void play() {
        System.out.println("Dog is playing...");
    }

    
    public void walk(){
        System.out.println("Dog is walking...");
    }

    
    public void makeSound(){
        System.out.println("Dog barks : Woof." );
    }

    
    public void getAnimalType(){
        Mammal.super.getAnimalType();
    }
}
