package Dependency_Inversion.GoodCode;

import Dependency_Inversion.GoodCode.interfaces.NotificationService;

public class EmailNotifier implements NotificationService {
    
    @Override
    public void sendNotification(String message){
        
    }
}
