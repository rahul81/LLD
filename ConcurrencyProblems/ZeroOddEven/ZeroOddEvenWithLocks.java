package ConcurrencyProblems.ZeroOddEven;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * Demonstrates thread coordination using ReentrantLock and Condition objects
 * More flexible than synchronized - allows specific thread signaling
 * Produces sequence: 0 1 0 2 0 3 0 4 0 5...
 */
public class ZeroOddEvenWithLocks {

    private int n;
    
    // Single ReentrantLock shared by all threads - provides mutual exclusion
    private ReentrantLock lock = new ReentrantLock();
    
    // Separate Condition objects for each thread type - enables targeted signaling
    // Unlike synchronized (single wait set), Conditions allow specific thread wakeup
    private Condition zeroCondition = lock.newCondition(); // Zero thread waits here
    private Condition evenCondition = lock.newCondition(); // Even thread waits here
    private Condition oddCondition = lock.newCondition();  // Odd thread waits here
    
    // State variables - protected by ReentrantLock (no volatile needed)
    // Lock provides memory barriers ensuring visibility across threads
    private boolean zeroTurn = true;  // Zero thread starts first
    private boolean oddTurn = false;  // Odd thread waits initially
    private boolean evenTurn = false; // Even thread waits initially

    ZeroOddEvenWithLocks(int n) {
        this.n = n;
    }

    /**
     * Zero thread - prints 0 and signals specific next thread (odd/even)
     * Uses targeted signaling via specific Condition objects
     */
    public void zero() throws InterruptedException {
        boolean isOdd = true; // Track whether next number should be odd
        
        for (int i = 1; i <= n; i++) {
            lock.lock(); // Acquire exclusive lock
            try {
                // Wait on zero's specific condition until it's zero's turn
                // Memory barrier: Fresh read of zeroTurn from main memory
                while (!zeroTurn) {
                    zeroCondition.await(); // Release lock and wait on zero's condition
                }
                
                System.out.println(0);
                zeroTurn = false; // Zero's turn is over
                
                // Signal specific thread based on sequence pattern
                if (isOdd) {
                    oddTurn = true;
                    oddCondition.signal(); // Wake up ONLY odd thread (targeted signaling)
                } else {
                    evenTurn = true;
                    evenCondition.signal(); // Wake up ONLY even thread (targeted signaling)
                }
                isOdd = !isOdd; // Alternate for next iteration
                
                // Memory barrier: All changes visible to other threads on unlock
            } finally {
                lock.unlock(); // Always release lock in finally block
            }
        }
    }

    /**
     * Even thread - prints even numbers when specifically signaled by zero thread
     * Waits on its own Condition object for targeted wakeup
     */
    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) { // Only even numbers: 2,4,6,8...
            lock.lock(); // Same lock as other threads for mutual exclusion
            try {
                // Wait on even's specific condition until zero signals it
                // Memory barrier: Fresh read of evenTurn from main memory
                while (!evenTurn) {
                    evenCondition.await(); // Wait on even's dedicated condition
                }
                
                System.out.println(i); // Print the even number
                evenTurn = false; // Even's turn is over
                zeroTurn = true;  // Give control back to zero thread
                
                // Signal zero thread specifically (not all threads like notifyAll)
                zeroCondition.signal(); // Wake up ONLY zero thread
                
                // Memory barrier: Changes visible to other threads on unlock
            } finally {
                lock.unlock(); // Guaranteed lock release even if exception occurs
            }
        }
    }

    /**
     * Odd thread - prints odd numbers when specifically signaled by zero thread
     * Waits on its own Condition object for targeted wakeup
     */
    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) { // Only odd numbers: 1,3,5,7...
            lock.lock(); // Same lock ensures mutual exclusion with other threads
            try {
                // Wait on odd's specific condition until zero signals it
                // Memory barrier: Fresh read of oddTurn from main memory
                while (!oddTurn) {
                    oddCondition.await(); // Wait on odd's dedicated condition
                }
                
                System.out.println(i); // Print the odd number
                oddTurn = false; // Odd's turn is over
                zeroTurn = true; // Give control back to zero thread
                
                // Signal zero thread specifically (more efficient than notifyAll)
                zeroCondition.signal(); // Wake up ONLY zero thread
                
                // Memory barrier: Changes visible to other threads on unlock
            } finally {
                lock.unlock(); // Guaranteed lock release for exception safety
            }
        }
    }

    /**
     * Main method demonstrating ReentrantLock with Condition-based coordination
     * 
     * Key advantages over synchronized approach:
     * 1. Targeted signaling - wake specific threads instead of all threads
     * 2. Multiple wait sets - each thread waits on its own Condition
     * 3. More efficient - no unnecessary thread wakeups
     * 4. Better performance - less context switching
     * 
     * Expected output: 0 1 0 2 0 3 0 4 0 5
     */
    public static void main(String[] args) throws InterruptedException {
        ZeroOddEvenWithLocks withLocks = new ZeroOddEvenWithLocks(5);
        
        // Create three threads that coordinate via ReentrantLock and Conditions
        Thread zero = new Thread(() -> {
            try {
                withLocks.zero(); // Controls sequence and signals specific threads
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread even = new Thread(() -> {
            try {
                withLocks.even(); // Waits on evenCondition for targeted wakeup
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread odd = new Thread(() -> {
            try {
                withLocks.odd(); // Waits on oddCondition for targeted wakeup
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Start all threads - they coordinate via lock and specific conditions
        zero.start();
        odd.start();
        even.start();

        // Wait for all threads to complete their execution
        zero.join();
        even.join();
        odd.join();
    }
}
