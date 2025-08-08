package Semaphores.CountingSemaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        // for (int i = 0; i < 3; i++) {
        // final int threadNum = i + 1;
        // Thread t = new Thread(() ->
        // CountingSemaphoreExample.accessCriticalSection("Thread-" + threadNum));
        // t.start();
        // }

        // with Executor service

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            final int ThreadNum = i + 1;
            executor.submit(() -> {
                CountingSemaphoreExample.accessCriticalSection("Thread-" + ThreadNum);
            });
        }

        executor.shutdown();
    }

}
