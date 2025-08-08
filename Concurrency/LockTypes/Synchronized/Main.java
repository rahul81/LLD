package LockTypes.Synchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            example.longTask("Task A");
        });

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            // TODO: handle exception
        }

        executor.submit(() -> {
            example.longTask2("Task B");
        });

        executor.shutdown();
    }

}
