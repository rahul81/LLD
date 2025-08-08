package LockTypes.TryLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ReentrantTryLockExample example = new ReentrantTryLockExample();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            example.longTask("Task A");
        });

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }

        executor.submit(() -> {
            example.tryLockTask("Task B");
        });

        executor.shutdown();
    }

}
