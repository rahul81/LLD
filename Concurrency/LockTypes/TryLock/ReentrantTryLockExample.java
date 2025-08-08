package LockTypes.TryLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void longTask(String taskName) {
        lock.lock();
        try {
            System.out.println(taskName + " Accuquired lock and is performing a long task.");

            Thread.sleep(5000);
            System.out.println(taskName + " finished the task and is releasing lock.");
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted.");
            Thread.currentThread().interrupt();

        } finally {
            lock.unlock();
        }
    }

    // Task that attempts to acquire the lock using tryLock with a timeout.
    public void tryLockTask(String taskName) {

        try {
            // Try to acquire the lock for 2 seconds.
            if (lock.tryLock(2, TimeUnit.SECONDS)) {

                System.out.println(taskName + " Accquired lock using TryLock and now performing task. ");
                lock.unlock();
            } else {
                System.out.println(taskName + " Could not accquire lock using TryLock.");
            }
        } catch (Exception e) {
            System.out.println(taskName + " was interrupted while waiting for lock.");
            Thread.currentThread().interrupt();
        }
    }
}


/**
 * 
 * Key Takeaways 💡
synchronized:
• Would force Task‑B to wait indefinitely until Task‑A releases the lock. ⏳

• Lacks the non‑blocking or timed acquisition option. ❌

‍

ReentrantLock:
• With tryLock(long time, TimeUnit unit), Task‑B can attempt to acquire the lock but proceed (or take alternate action) if it's not available within a specified timeout. ⏱️

• Offers greater flexibility and control over lock acquisition and release. 🎛️
 */