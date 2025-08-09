package Semaphores.ReadWriteLock;

import java.util.concurrent.Semaphore;

public class ReaderWriterLock {
    // count of active readers
    private int readers = 0;

    private String value = "Test";

    private final Semaphore mutex = new Semaphore(1);

    private final Semaphore wrt = new Semaphore(1);

    public void accquireReadLock() throws InterruptedException {
        mutex.acquire();
        readers++;

        if (readers == 1) {
            wrt.acquire();
        }

        mutex.release();
    }

    public void releaseReadLock() throws InterruptedException {
        mutex.acquire();
        readers--;

        if (readers == 0) {
            wrt.release();
        }

        mutex.release();

    }

    public void acquireWriteLock() throws InterruptedException {
        wrt.acquire();
    }

    public void releaseWriteLock() throws InterruptedException {
        wrt.release();
    }

    public void read() throws InterruptedException {
        accquireReadLock();
        System.out.println(Thread.currentThread().getName() + " is Reading");
        Thread.sleep(3000);
        System.out.println("Reading value: " + value);
        System.out.println(Thread.currentThread().getName() + " finished reading");
        releaseReadLock();
    }

    public void write() throws InterruptedException {
        acquireWriteLock();
        System.out.println(Thread.currentThread().getName() + " is Writing");
        Thread.sleep(1000);
        System.out.println("Writing value: " + value);
        System.out.println(Thread.currentThread().getName() + " finished writing");
        releaseWriteLock();
	
    }
}
