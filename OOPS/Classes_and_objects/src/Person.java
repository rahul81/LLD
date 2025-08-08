
public class Person{

    private String name;
    private int age;

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void sayHello(){
        System.out.println("Hello, my name is " + this.name + " ! and I am " + this.age + " years old.");
    }
    
}