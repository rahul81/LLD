package Constructors.PrivateConstructor.src;

public class Main {
    public static void main(String[] args) {
        // Singleton s1 = new Singleton(); 
        // Above line will throw error -> Constructor not visible since Main class is outside of Singleton class
        // and object creation is allowed only from inside of class

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // True since refering the same instance
    }
}
