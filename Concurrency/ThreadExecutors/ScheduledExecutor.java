package ThreadExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Task t1 = new Task(1);
        Task t2 = new Task(2);
        // Task t3 = new Task(3);

        ScheduledFuture<String> future1 = executor.schedule(t1, 0, TimeUnit.SECONDS);
        ScheduledFuture<String> future2 = executor.schedule(t2, 0, TimeUnit.SECONDS);

        try {
            System.out.println(future1.get());
            Thread.sleep(2000);
            System.out.println(future2.get());

            executor.shutdown();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
