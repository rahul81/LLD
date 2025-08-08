package Synchronization.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    // Atomic across all threads
    // Can be used for performing simple inc, dec etc operations
    // Or when locking Overhead is too much.
    // The AtomicInteger counter provides atomic methods for thread-safe operations.
    // makes sure the operation is on single resource is not performed by 2 or more threads at a time.
    private AtomicInteger Counter = new AtomicInteger(0);

    public void increment() {
        System.out.println("Starting Increment on Thread : " + Thread.currentThread().getName());

        int newValue = Counter.getAndIncrement();
        System.out.println("Counter value after increment : " + newValue);
        System.out.println("Ending Thread : " + Thread.currentThread().getName());
    }

    public int getCount() {
        return Counter.get();
    }
}
