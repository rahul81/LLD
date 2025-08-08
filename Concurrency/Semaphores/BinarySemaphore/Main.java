package Semaphores.BinarySemaphore;

public class Main {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> BinarySemaphoreExample.accessCriticalSection("Thread-1"));
        Thread t2 = new Thread(() -> BinarySemaphoreExample.accessCriticalSection("Thread-2"));

        t1.start();
        t2.start();
    }

}
