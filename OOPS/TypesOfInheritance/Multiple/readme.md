

Multiple Inheritance : 
Java does not support Multiple inheritance directly due to the diamond problem, but it can be achieved using interfaces. In Multiple inheritance, A single class can inherit properties from multiple interfaces.

‍
💎 What is the Diamond Problem?
The diamond problem arises in languages that allow multiple inheritance with classes. Imagine a scenario where a class inherits from two parent classes that both have a method with the same name. If the child class does not override the method, it creates ambiguity as to which implementation the child class should inherit. This leads to confusion and potential conflicts in the program.

```java
class Animal {
  public void sound() {
    System.out.println("Animal makes a sound");
  }
}

class Dog extends Animal {
  @Override
  public void sound() {
    System.out.println("Dog barks");
  }
}

class Cat extends Animal {
  @Override
  public void sound() {
    System.out.println("Cat meows");
  }
}

// Not supported in Java
public class HybridAnimal extends Dog, Cat {
  public static void main(String[] args) {
    HybridAnimal hybrid = new HybridAnimal();
    hybrid.sound(); // Creates ambiguity: Should it call Dog's sound() or Cat's
                    // sound()?
  }
}
```

🧠 How Java Resolves This?

Java avoids this problem by not allowing multiple inheritance with classes. Instead, Java provides interfaces as a way to achieve multiple inheritance.


When a class implements multiple interfaces, it must provide implementations for the methods defined in the interfaces. This eliminates ambiguity since the child class explicitly defines the behavior of inherited methods.