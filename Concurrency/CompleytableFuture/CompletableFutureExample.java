package CompleytableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Using CompletableFuture with custom thread pool
        CompletableFuture<String> future1 = CompletableFuture
            .supplyAsync(() -> {
                System.out.println("Task 1 on: " + Thread.currentThread().getName());
                return "Result 1";
            }, executor);

        CompletableFuture<String> future2 = CompletableFuture
            .supplyAsync(() -> {
                System.out.println("Task 2 on: " + Thread.currentThread().getName());
                return "Result 2";
            }, executor);

        // Chain operations
        CompletableFuture<String> combined = future1
            .thenCombineAsync(future2, (r1, r2) -> r1 + " + " + r2, executor);

        System.out.println(combined.join());
        executor.shutdown();
    }
}