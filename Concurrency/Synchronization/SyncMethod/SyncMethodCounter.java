package Synchronization.SyncMethod;

public class SyncMethodCounter {

    private int count = 0;

    public synchronized void increment() {
        System.out.println("Running Thread " + Thread.currentThread().getName());

        System.out.println("Incrementing Counter...");

        count++;
        System.out.println("Counter value after increment : " + count);
        System.out.println("Ending Thread " + Thread.currentThread().getName());

    }

    public int getCount() {
        return count;
    };

}
