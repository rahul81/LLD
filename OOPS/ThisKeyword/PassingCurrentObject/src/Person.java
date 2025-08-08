package ThisKeyword.PassingCurrentObject.src;

public class Person {
    String name;

    Person(String name){
        this.name = name;
    }

    void makeCall(SmartPhone sp){
        sp.call(this); // passing current object reference
    }

    public static void main(String[] args) {
        Person p = new Person("Micheal");
        SmartPhone sp = new SmartPhone();

        p.makeCall(sp); // Dependecy pattern

    }
}
