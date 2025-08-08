package Constructors.parameterized.src;

public class Parameterized {
    
    String name; // null
    int age; // 0 by default

    Parameterized(String name){
        this.name = name;
    }

    Parameterized(String name , int age){
        this.name = name;
        this.age = age;
    }

    void display(){

        System.out.println("Parameterized Constructor initialised value name-> " + name);
        System.out.println("Parameterized initialised value age -> " + age);
    }


    public static void main(String[] args) {
        Parameterized pc = new Parameterized("Kenway");
        pc.display();

        Parameterized pc2 = new Parameterized("Kenway", 35);
        pc2.display();
    }
}
