package TypesOfInheritance.Multiple.src;

public interface Cat {
   default void sound(){
    System.out.println("Meows");
   }
}