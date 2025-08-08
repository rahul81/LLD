package Abstraction.src;

public abstract class Animal {
    
    public void eat(){
        System.out.println("Animal is eating...");
    }

    // Not necessary to use by extending class
    void methodWithBody(){   
        System.out.println("");
    }

    // must implement by extending class
    abstract void makeSound();

    public void sleep(){
        System.out.println("Animal is sleeping...");
    }
}
