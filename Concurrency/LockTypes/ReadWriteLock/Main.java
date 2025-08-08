package LockTypes.ReadWriteLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ReadWriteExample example = new ReadWriteExample();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        /*
         * - Schedule tasks to simulate the following sequence:
         * - 1. Start with three reader tasks concurrently.
         * - 2. Then, a writer task updates the log.
         * - 3. Next, two readers read the updated value.
         * - 4. Then, a second writer task updates the log.
         * - 5. Finally, one more reader reads the new value.
         */
        // Submit three concurrent reader tasks.

        executor.submit(() -> {
            example.readValue("Reader-1");
        });
        executor.submit(() -> {
            example.readValue("Reader-2");
        });

        // Submit a writer
        executor.submit(() -> {
            example.writeValue("Writer-1", 100);
        });

        executor.submit(() -> {
            example.readValue("Reader-3");
        });
        executor.submit(() -> {
            example.readValue("Reader-4");
        });

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Submit a writer
        executor.submit(() -> {
            example.writeValue("Writer-2", 200);
        });

        executor.submit(() -> {
            example.readValue("Reader-5");
        });

        executor.shutdown();

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout wating for tasks to finish.");
            }
        } catch (Exception e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }

    }

}
