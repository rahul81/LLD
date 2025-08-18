package ConcurrencyProblems.DiningPhilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Driver class to demonstrate the Dining Philosophers solution
 * Creates 5 philosopher threads that compete for shared forks
 */
public class DiningPhilosophersDriver {
    
    public static void main(String[] args) {
        // Create the dining philosophers instance
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        
        // Create thread pool for 5 philosophers
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        // Philosopher names for better output readability
        String[] names = {"Aristotle", "Plato", "Socrates", "Descartes", "Kant"};
        
        System.out.println("🍽️  Starting Dining Philosophers Simulation");
        System.out.println("=" .repeat(50));
        
        // Create and start 5 philosopher threads
        for (int i = 0; i < 5; i++) {
            final int philosopherId = i;
            final String philosopherName = names[i];
            
            executor.submit(() -> {
                try {
                    // Each philosopher tries to eat multiple times
                    for (int meal = 1; meal <= 3; meal++) {
                        final int currentMeal = meal; // Make effectively final for lambda
                        System.out.println("🤔 " + philosopherName + " is thinking...");
                        
                        // Simulate thinking time
                        Thread.sleep((long) (Math.random() * 1000));
                        
                        System.out.println("🍽️  " + philosopherName + " wants to eat (meal " + currentMeal + ")");
                        
                        // Attempt to eat using the dining philosophers solution
                        diningPhilosophers.wantsToEat(
                            philosopherId,
                            // Pick left fork action
                            () -> System.out.println("🍴 " + philosopherName + " picked up LEFT fork " + philosopherId),
                            // Pick right fork action  
                            () -> System.out.println("🍴 " + philosopherName + " picked up RIGHT fork " + ((philosopherId + 1) % 5)),
                            // Eat action
                            () -> {
                                System.out.println("🍝 " + philosopherName + " is EATING (meal " + currentMeal + ")");
                                try {
                                    // Simulate eating time
                                    Thread.sleep((long) (Math.random() * 2000));
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            },
                            // Put left fork action
                            () -> System.out.println("🔽 " + philosopherName + " put down LEFT fork " + philosopherId),
                            // Put right fork action
                            () -> System.out.println("🔽 " + philosopherName + " put down RIGHT fork " + ((philosopherId + 1) % 5))
                        );
                        
                        System.out.println("✅ " + philosopherName + " finished meal " + currentMeal);
                        
                        // Simulate time between meals
                        Thread.sleep((long) (Math.random() * 500));
                    }
                    
                    System.out.println("🎉 " + philosopherName + " has finished all meals!");
                    
                } catch (InterruptedException e) {
                    System.out.println("❌ " + philosopherName + " was interrupted");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Shutdown executor and wait for completion
        executor.shutdown();
        try {
            // Wait up to 30 seconds for all philosophers to finish
            if (executor.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("\n🏁 All philosophers have finished dining!");
                System.out.println("✨ No deadlock occurred - solution successful!");
            } else {
                System.out.println("\n⏰ Simulation timed out");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("\n❌ Main thread interrupted");
            executor.shutdownNow();
        }
    }
}