package ThreadExecutors;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPool {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Task t1 = new Task(1);
        Task t2 = new Task(2);
        Task t3 = new Task(3);
        Task t4 = new Task(4);
        Task t5 = new Task(5);

        List<Callable<String>> allTasks = List.of(t1, t2, t3, t4, t5);

        try {

            List<Future<String>> futures = executorService.invokeAll(allTasks);

            executorService.shutdown();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
