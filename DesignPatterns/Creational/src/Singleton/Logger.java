package Singleton;

public class Logger {

    public static Logger logger;

    private Logger() {

    }

    public static Logger getLogger() {

        if (logger == null) {
            logger = new Logger();
        }

        return logger;
    }

    public void logMessage(String message) {

    }
}
