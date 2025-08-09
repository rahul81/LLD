package Semaphores.BarrierPattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) {

        final int numThreads = 5;
        SemaphoreBarrierPattern.SemaphoreBarrier barrier = new SemaphoreBarrierPattern.SemaphoreBarrier(numThreads);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads, new ThreadFactory() {
            private int counter = 1;

            @Override
            public Thread newThread(Runnable runnable) {
                Thread t = new Thread(runnable, "Thread-" + counter);
                counter++;
                return t;
            }

        });

        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " doing phase 1 work");
                    Thread.sleep(1000);
                    System.out.println(
                            Thread.currentThread().getName() + " finished phase 1 work, waiting at barrier");
                    barrier.await();

                    System.out.println(Thread.currentThread().getName() + " doing phase 2 work");
                    Thread.sleep(1000);
                    System.out.println(
                            Thread.currentThread().getName() + " finished phase 2 work");
                    barrier.await();

                    // Final work
                    System.out.println(Thread.currentThread().getName() + " doing phase 3 work");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " interrupted: " + e.getMessage());
                }
            });
        }
        executor.shutdown();
    }
}
