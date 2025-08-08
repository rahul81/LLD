package Synchronization.Volatile;

public class VolatileExample {
    
    // Only to be called from main thread to update the flag status
    // will be shared across all threads
    private volatile boolean running = true;

    public void runTask(){

        System.out.println("Worker Thread: Starting execution...");
        int i = 0;

        while(running){
            i++;
        }
        System.out.println("Worker Thread: Detected Stop signal. Final counter value :" + i);
    }

    public void stopTask(){
        running = false;
    }
}

