package CompleytableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class MultipleThreadsExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create multiple futures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 on: " + Thread.currentThread().getName());
            return "Result 1";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2 on: " + Thread.currentThread().getName());
            return "Result 2";
        }, executor);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 3 on: " + Thread.currentThread().getName());
            return "Result 3";
        }, executor);

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 4 on: " + Thread.currentThread().getName());
            return "Result 4";
        }, executor);

        // Wait for all to complete
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3, future4);

        // Collect results
        CompletableFuture<List<String>> allResults = allFutures.thenApply(v -> 
            List.of(future1.join(), future2.join(), future3.join(), future4.join())
        );

        System.out.println("All results: " + allResults.join());
        executor.shutdown();
    }
}