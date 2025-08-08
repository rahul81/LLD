package Semaphores.SemaphoreVsLock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreVsLockExample {

    private final Semaphore semaphore = new Semaphore(3);
    private final ReentrantLock lock = new ReentrantLock();

    public void accessWithSemaphore(String taskName) {
        try {
            semaphore.acquire();
            System.out.println(taskName + " Accquired lock using Semaphore.");
            System.out.println(taskName + " Performing task.");
            Thread.sleep(2000);
            System.out.println(taskName + " Releasing semaphore lock.");

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(taskName + " Released Semaphore lock.");
            semaphore.release();
        }
    }

    public void accessWithLock(String taskName) {
        try {
            lock.lock();
            System.out.println(taskName + " Accquired lock using reeentrant Lock");
            System.out.println(taskName + " Performing Task.");
            Thread.sleep(2000);
            System.out.println(taskName + " Releasing Reentrant lock");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

}
