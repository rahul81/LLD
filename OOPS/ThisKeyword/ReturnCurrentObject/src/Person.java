package ThisKeyword.ReturnCurrentObject.src;

public class Person {
    String name;


    Person setName(String name){
        this.name = name;
        return this;
    }


    void display(){
        System.out.println("Name : "+ name);
    }

    public static void main(String[] args) {
        Person p = new Person();

        p.setName("Rahul").display(); // Seamless method chaining.
    }
}
