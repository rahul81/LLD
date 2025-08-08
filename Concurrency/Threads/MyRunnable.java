package Threads;

public class MyRunnable implements Runnable {
    
    @Override
    public void run(){
        Count.runCount(5);
    }

    // This can extend other classes and interfaces too
}
