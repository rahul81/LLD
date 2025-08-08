package Dependency_Inversion.GoodCode;

import Dependency_Inversion.GoodCode.interfaces.LoggingService;

public class DatabaseLogger implements LoggingService {
    
    @Override
    public void logMessage(String message){

    }

    @Override 
    public void logError(String error){
        
    }
}
