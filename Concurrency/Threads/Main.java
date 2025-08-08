package Threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Direct implementation

        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        thread1.start();
        thread2.start();

        thread2.join(); // waits for thread to complete

        // Using runnable interface

        MyRunnable runnable = new MyRunnable();

        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);

        thread3.start();
        thread4.start();

        thread4.join();

        // Using callable
        // Create executor thread pool with 2 Threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Create callable tasks
        Callable<String> callable1 = new MyCallable("Task 1");
        Callable<String> callable2 = new MyCallable("Task 2");

        try {

            Future<String> future1 = executorService.submit(callable1);
            Future<String> future2 = executorService.submit(callable2);

            // Get results from Future objects
            System.out.println("Result from first task:");
            System.out.println(future1.get()); // Blocks until the task completes

            System.out.println("Result from second task:");
            System.out.println(future2.get()); // Blocks until the task completes

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task execution interrupted: " + e.getMessage());
        } finally {
            // Shutdown the executor
            executorService.shutdown();
        }

    }
}
