package ThreadPools.CachedPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class IOBoundExample {

    public static void main(String[] args) {

        // For IO based tasks like db read/write, file io, threads are created
        // dynamically.
        ExecutorService executors = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executors.execute(() -> {
                SimulateWebRequests();
                System.out.println(Thread.currentThread().getName() + "Completed IO task");
            });
        }

        executors.shutdown();

    }

    private static void SimulateWebRequests() {

        try {
            System.out.println(Thread.currentThread().getName() + " is waiting for response");
            // TimeUnit.MILLISECONDS.sleep(2000); // simulate network delay
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("Thread interrupted : " + e.getMessage());

        }
    }

}
