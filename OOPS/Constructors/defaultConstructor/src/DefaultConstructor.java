package Constructors.defaultConstructor.src;

public class DefaultConstructor {
    String name;

    void display() {
        System.out.println("Default constructor initialised value -> " + name);
    }


    public static void main (String[] args){

        DefaultConstructor dc = new DefaultConstructor();
        dc.display(); // null

    }
}
