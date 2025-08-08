package ThreadPools.ThreadPoolLifeCycle;

import java.util.concurrent.Callable;

public class TaskSleep implements Callable<String> {

    private final int taskId;

    TaskSleep(int id) {
        taskId = id;
    }

    public synchronized void notifyExample() {

        System.out.println("Notifying Thread : " + Thread.currentThread().getName());
        this.notify();
    }

    @Override
    public String call() {

        System.out.println(Thread.currentThread().getName() + " Thread is starting task : " + taskId);

        try {
            Thread.sleep(2000); // simulate work RUNNABLE to TIMED WAITING (sleep)

            synchronized (this) {
                // The thread is now RUNNING and enters a synchronized block
                System.out.println(Thread.currentThread().getName() + " Thread is sleeping on task : " + taskId);
                // The thread leaves the RUNNING state and enters the sleeping state
                Thread.sleep(2000); // blocks resource in case of Shared usage
                System.out.println(
                        Thread.currentThread().getName() + " Thread is done sleeping on task : " + taskId + "\n");
            }

            System.out.println("\n" + Thread.currentThread().getName() + " Thread has completed task :" + taskId);

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted : " + e.getMessage());
        }

        return "Thread " + Thread.currentThread().getName() + " Completed";

    }

}
