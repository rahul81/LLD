package Singleton;

public class Main {
    
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.logMessage("Test");

        Logger logger2 = Logger.getLogger();

        // instance of this class has only one object, always popints to same object
        System.out.println(logger == logger2); // returns true 
    }
}
