package LockTypes.ReentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ReentrantLockExecutorExample example = new ReentrantLockExecutorExample();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                example.increment();
            });
        }

        executor.shutdown();

        try {

            executor.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println("Final counter value : " + example.getCounter());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Not all tasks finished withing time.");
            Thread.currentThread().interrupt();
        }

    }
}
