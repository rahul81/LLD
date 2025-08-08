package Constructors.PrivateConstructor.src;

class Singleton {

    private static Singleton instance;
    String name;
    private Singleton() {}; // constructor is accessible only from inside the class

    // In this pattern only 1 instance of class can be created and same will referred in crearted objects
    public static Singleton getInstance() {

        if (instance == null){
            instance = new Singleton();
        }

        return instance;
    }
}
