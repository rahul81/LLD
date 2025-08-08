package ThreadPools.ThreadPoolLifeCycle;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;

public class ThreadPool {

    @SuppressWarnings("unused")
    private void sharedSleepResource(ExecutorService executorService) {

        // Shared resource only one thread can enter at a
        // time
        // if used sleep on shared resource it blocks the resource from other threads

        TaskSleep task = new TaskSleep(0);

        @SuppressWarnings("unused")
        Future<String> future1 = executorService.submit(task);
        @SuppressWarnings("unused")
        Future<String> future2 = executorService.submit(task);

    }

    void nonSharedSleepResouce(ExecutorService executorService) {

        List<TaskSleep> Tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Tasks.add(new TaskSleep(i));
        }

        try {
            executorService.invokeAll(Tasks);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @SuppressWarnings("unused")
    private void sharedWaitResource(ExecutorService executorService) {

        TaskWait task = new TaskWait(0);

        List<TaskWait> Tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
            Tasks.add(task);
        }

        try {
            // Runs all tasks

            // executorService.invokeAll(Tasks);
            for (TaskWait t : Tasks) {
                try {
                    Thread.sleep(3000);
                    System.out.println("\nNotifying Thread...");
                    t.notifyExample();

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @SuppressWarnings("unused")
    private void nonSharedWaitResouce(ExecutorService executorService) {

        List<TaskWait> Tasks = new ArrayList<TaskWait>();

        for (int i = 0; i < 5; i++) {

            TaskWait task = new TaskWait(i); // not a shared resource
            executorService.submit(task);
            Tasks.add(task);

            System.out.println("Submitted task : " + i);

        }

        for (TaskWait t : Tasks) {
            try {
                Thread.sleep(3000);
                System.out.println("\nNotifying Thread...");
                t.notifyExample();

            } catch (Exception e) {
                // TODO: handle exception
            }

        }

    }

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);

        System.out.println("Thread pool created ");

        ThreadPool tp = new ThreadPool();

        try {
            // tp.sharedSleepResource(executors); // incase of shared resource sleep it
            // synhronised block executes one at a time
            // Thread.sleep(2000);

            tp.nonSharedSleepResouce(executors); // in this case resource is not shared, code can execute parallely even
                                                 // in sync block

            // tp.sharedWaitResource(executors);
            // Thread.sleep(2000);

            // tp.nonSharedWaitResouce(executors);

        } catch (Exception e) {
            // TODO: handle exception
        }

        executors.shutdown();
    }

}
