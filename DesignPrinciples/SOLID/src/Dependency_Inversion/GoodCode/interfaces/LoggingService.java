package Dependency_Inversion.GoodCode.interfaces;

public interface LoggingService {
    void logMessage(String message);
    void logError(String error);
}
