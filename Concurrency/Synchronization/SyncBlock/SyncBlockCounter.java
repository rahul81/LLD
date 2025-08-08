package Synchronization.SyncBlock;

public class SyncBlockCounter {
    private int count = 0;

    // Explicit lock object for finer control. 
    private final Object lock = new Object();

    public void increment(){

        System.out.println("Non sync code Starting...");

        synchronized (lock){
            System.out.println("Synchronized block started on Thread : "+ Thread.currentThread().getName());

            count++;
            System.out.println("Counter value after increment : "+ count);
            System.out.println("Synchronized block ending on Thread : "+ Thread.currentThread().getName());
        }

        System.out.println("Non sync code Ending...");

    }

    public int getCount(){
        return count;
    }
}
