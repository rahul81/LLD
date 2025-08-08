package ThisKeyword.ConstructorChaining.src;

public class Person {
    String name;
    int age;

    Person(String name) {
        this(name, 20); // Call Constructor in same class
    }

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    void display(){
        System.out.println("Name : "+ name);
        System.out.println("Age : "+ age);
    }

    public static void main(String[] args) {
        Person p = new Person("Rahul");

        p.display();
    }
}
