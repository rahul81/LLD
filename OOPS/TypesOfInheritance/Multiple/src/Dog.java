package TypesOfInheritance.Multiple.src;

public interface Dog {
    default void sound(){
        System.out.println("Barks");
    }

}