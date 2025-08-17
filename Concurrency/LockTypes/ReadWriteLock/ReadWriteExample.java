package LockTypes.ReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteExample {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int logValue = 0;

    private void simulateWork() {
        @SuppressWarnings("unused")
        long sum = 0;
        for (int i = 0; i < 500000; i++) {
            sum += i;
        }
    }

    public void writeValue(String taskName, int newValue) {

        rwLock.writeLock().lock();

        try {
            System.out.println(taskName + " (write) : Accquired write lock.");
            simulateWork();
            logValue = newValue;
            System.out.println(taskName + "(write): Updated logValue to : " + logValue);
        } finally {
            System.out.println(taskName + " (write): Released write lock.");
            rwLock.writeLock().unlock();

        }
    }

    public void readValue(String taskName) {
        rwLock.readLock().lock();
        try {
            System.out.println(taskName + " (read): Accquired read lock. Reading logValue: " + logValue);
            simulateWork();
            Thread.sleep(2000); // While Reentrant read lock is accquired, write thread can't accquire write lock
            System.out.println(taskName + " (read): Finished reading.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(taskName + " (read): Released read lock.");
            rwLock.readLock().unlock();

        }
    }
}
