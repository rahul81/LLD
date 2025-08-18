package ConcurrencyProblems.DiningPhilosophers;

import java.util.concurrent.Semaphore;

/**
 * Solution to the classic Dining Philosophers problem using semaphores.
 * 
 * Problem: 5 philosophers sit around a circular table with 5 forks.
 * Each philosopher needs 2 forks (left and right) to eat.
 * Goal: Prevent deadlock and ensure all philosophers can eventually eat.
 * 
 * Solution approach:
 * 1. Limit concurrent eaters to 4 philosophers (prevents circular wait)
 * 2. Use individual semaphores for each fork to control access
 */
public class DiningPhilosophers {

    // Semaphore to limit number of philosophers trying to eat simultaneously
    // Only 4 out of 5 philosophers can attempt to eat at once to prevent deadlock
    private Semaphore wantsToEatPermit;
    
    // Array of semaphores, one for each fork (binary semaphores with permit = 1)
    // Each fork can only be held by one philosopher at a time
    private Semaphore[] forkSemaphores;

    /**
     * Constructor initializes the semaphores for deadlock-free dining
     */
    public DiningPhilosophers() {
        // Allow only 4 philosophers to compete for forks simultaneously
        // This breaks the circular wait condition and prevents deadlock
        wantsToEatPermit = new Semaphore(4);

        // Initialize 5 fork semaphores (one for each fork)
        forkSemaphores = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            forkSemaphores[i] = new Semaphore(1); // Binary semaphore for mutual exclusion
        }
    }

    /**
     * Method called when a philosopher wants to eat
     * 
     * @param philosopher The philosopher's ID (0-4)
     * @param pickLeftFork Action to pick up left fork
     * @param pickRightFork Action to pick up right fork
     * @param eat Action to eat
     * @param putLeftFork Action to put down left fork
     * @param putRightFork Action to put down right fork
     * @throws InterruptedException if thread is interrupted while waiting
     */
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
            Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {

        // Step 1: Get permission to attempt eating (max 4 philosophers)
        wantsToEatPermit.acquire();
        
        // Step 2: Calculate fork indices
        // Left fork has same index as philosopher
        int leftFork = philosopher;
        // Right fork is the next fork in circular arrangement
        int rightFork = (philosopher + 1) % 5;
        
        // Step 3: Acquire both forks in order (left first, then right)
        // This consistent ordering helps prevent some deadlock scenarios
        forkSemaphores[leftFork].acquire();
        forkSemaphores[rightFork].acquire();

        // Step 4: Perform eating actions (now that both forks are secured)
        pickLeftFork.run();   // Pick up left fork
        pickRightFork.run();  // Pick up right fork
        eat.run();            // Eat the food
        
        // Step 5: Release resources in reverse order
        putLeftFork.run();                    // Put down left fork
        forkSemaphores[leftFork].release();   // Release left fork semaphore
        putRightFork.run();                   // Put down right fork
        forkSemaphores[rightFork].release();  // Release right fork semaphore
        
        // Step 6: Release eating permission for other philosophers
        wantsToEatPermit.release();
    }
}
