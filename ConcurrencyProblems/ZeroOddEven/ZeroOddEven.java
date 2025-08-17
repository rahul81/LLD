package ConcurrencyProblems.ZeroOddEven;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Demonstrates thread coordination using Semaphores for permit-based synchronization
 * Most elegant solution - no shared state variables needed!
 * Produces sequence: 0 1 0 2 0 3 0 4 0 5...
 * 
 * Key advantage: Cross-semaphore signaling - Thread A can release permits 
 * on Semaphore X that Thread B acquires, enabling inter-thread communication
 */
public class ZeroOddEven {

    private int n;
    
    // Three separate semaphores act as "permission tokens" for each thread type
    // Unlike synchronized (same monitor required), semaphores allow cross-signaling
    private Semaphore zeroSemaphore;  // Controls zero thread execution
    private Semaphore evenSemaphore;  // Controls even thread execution  
    private Semaphore oddSemaphore;   // Controls odd thread execution

    public ZeroOddEven(int n) {
        this.n = n;

        // Initial permit distribution determines execution order
        zeroSemaphore = new Semaphore(1); // Zero starts with 1 permit (can execute first)
        evenSemaphore = new Semaphore(0); // Even starts with 0 permits (must wait)
        oddSemaphore = new Semaphore(0);  // Odd starts with 0 permits (must wait)
    }

    /**
     * Zero thread - prints 0 and gives permission to next thread
     * Demonstrates cross-semaphore signaling: acquires from own semaphore,
     * releases to different semaphores (odd/even)
     */
    public void zero(IntConsumer printNumber) throws InterruptedException {
        boolean isOdd = true; // Track which thread to signal next

        for (int i = 1; i <= n; i++) {
            // Wait for permission to execute (blocks if no permits available)
            zeroSemaphore.acquire(); // Decrements zeroSemaphore permits by 1
            
            printNumber.accept(0); // Print zero
            
            // Give permission to next thread based on sequence pattern
            // Cross-semaphore signaling: release permit on different semaphore
            if (isOdd) {
                oddSemaphore.release();  // Give permit to odd thread (1,3,5...)
            } else {
                evenSemaphore.release(); // Give permit to even thread (2,4,6...)
            }
            isOdd = !isOdd; // Alternate between odd and even for next iteration
        }
    }

    /**
     * Even thread - prints even numbers when given permission by zero thread
     * Waits for permits on its own semaphore, then gives permission back to zero
     */
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) { // Only even numbers: 2,4,6,8...
            // Wait for zero thread to give permission (blocks until permit available)
            evenSemaphore.acquire(); // Decrements evenSemaphore permits by 1
            
            printNumber.accept(i); // Print the even number
            
            // Give permission back to zero thread to continue sequence
            // Cross-semaphore signaling: acquired from even, releasing to zero
            zeroSemaphore.release(); // Increments zeroSemaphore permits by 1
        }
    }

    /**
     * Odd thread - prints odd numbers when given permission by zero thread
     * Waits for permits on its own semaphore, then gives permission back to zero
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) { // Only odd numbers: 1,3,5,7...
            // Wait for zero thread to give permission (blocks until permit available)
            oddSemaphore.acquire(); // Decrements oddSemaphore permits by 1
            
            printNumber.accept(i); // Print the odd number
            
            // Give permission back to zero thread to continue sequence
            // Cross-semaphore signaling: acquired from odd, releasing to zero
            zeroSemaphore.release(); // Increments zeroSemaphore permits by 1
        }
    }

    /**
     * Semaphore advantages over synchronized/ReentrantLock approaches:
     * 
     * 1. NO SHARED STATE VARIABLES - no boolean flags needed
     * 2. CROSS-SEMAPHORE SIGNALING - threads can signal different semaphores
     * 3. PERMIT-BASED - simple acquire/release model
     * 4. CLEANER CODE - less complex than wait/notify or Condition objects
     * 5. NATURAL COORDINATION - permits flow between threads like tokens
     * 
     * Execution flow:
     * Zero: acquire(zero) → print(0) → release(odd/even)
     * Odd:  acquire(odd) → print(1,3,5...) → release(zero)
     * Even: acquire(even) → print(2,4,6...) → release(zero)
     */
}
