package Semaphores.BinarySemaphore;

import java.util.concurrent.Semaphore;

/*
 * A binary semaphore has only two states (0 or 1 permit) and 
 * is mainly used to enforce mutual exclusion, similar to a mutex or lock.
 */

public class BinarySemaphoreExample {
    
    // if permits are more than 1 then more than 1 threads can accquire lock on the resource.
    public static final Semaphore mutex = new Semaphore(1); // Binary semaphore with 1 permit

    public static void accessCriticalSection(String threadName) {
        try {
            System.out.println(threadName + " is attempting to accquire the lock.");
            mutex.acquire();
            System.out.println(threadName + " Accquired lock.");
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            mutex.release();
            System.out.println(threadName + " Released the lock.");
        }
    }
}
