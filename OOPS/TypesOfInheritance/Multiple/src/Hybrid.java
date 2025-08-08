package TypesOfInheritance.Multiple.src;

public class Hybrid implements Dog, Cat {
    
   public void sound(){
    Dog.super.sound();
    Cat.super.sound();
   }
}
