package LockTypes.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;


/**
 * It is an Explicit lock
 * It is used when you need advanced control over locking 🧠
 *  (e.g., trying to acquire a lock and/or setting up fairness) or
 *  when a portion of a critical section is complex and may require more nuanced lock handling 
 * 
 */

public class ReentrantLockExecutorExample {
    
    private int counter = 0 ;

    private final ReentrantLock lock = new ReentrantLock();

    public void increment (){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " accquired lock.");
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented Counter to : " + counter);

        } finally {
            System.out.println(Thread.currentThread().getName() + " released lock.");
            lock.unlock();
        }
    }

    public int getCounter(){
        return counter;
    }

}
