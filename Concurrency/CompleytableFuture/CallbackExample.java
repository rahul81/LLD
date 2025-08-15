package CompleytableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 running on: " + Thread.currentThread().getName());
            return "Result 1";
        }, executor).thenApply(result -> {
            System.out.println("Task 1 completed: " + result);
            return result;
        });

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2 running on: " + Thread.currentThread().getName());
            return "Result 2";
        }, executor).thenRun(() -> {
            System.out.println("Task 2 finished!");
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 3 running on: " + Thread.currentThread().getName());
            return "Result 3";
        }, executor).whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Task 3 success: " + result);
            } else {
                System.out.println("Task 3 failed: " + ex.getMessage());
            }
        });

        CompletableFuture.allOf(future1, future2, future3).join();
        executor.shutdown();
    }
}