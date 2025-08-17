package ConcurrencyProblems.ZeroOddEven;

/**
 * Demonstrates thread coordination using synchronized blocks and wait/notify
 * Produces sequence: 0 1 0 2 0 3 0 4 0 5...
 */
public class ZeroOddEvenSynchronized {
    private int n;
    
    // State variables to control which thread can execute
    // These are NOT volatile because they're always accessed inside synchronized blocks
    // synchronized provides memory barriers ensuring visibility across threads
    private boolean zeroTurn = true;  // Zero thread starts first
    private boolean oddTurn = false;  // Odd thread waits initially
    private boolean evenTurn = false; // Even thread waits initially
    
    // Single shared lock for all threads - enables cross-thread communication
    // All threads must use SAME lock for wait/notify to work
    private final Object lock = new Object();

    ZeroOddEvenSynchronized(int n) {
        this.n = n;
    }

    /**
     * Zero thread - prints 0 and signals next thread (odd/even alternately)
     */
    public void zero() throws InterruptedException {
        boolean isOdd = true; // Track whether next number should be odd

        for (int i = 1; i <= n; i++) {
            synchronized (lock) { // Acquire shared lock for thread safety
                try {
                    // Wait until it's zero's turn (prevents spurious wakeups with while)
                    // Memory barrier: Fresh read of zeroTurn from main memory
                    while (!zeroTurn) {
                        lock.wait(); // Release lock and wait for notification
                    }
                    
                    System.out.println(0);
                    zeroTurn = false; // Zero's turn is over
                    
                    // Signal next thread based on sequence pattern
                    if (isOdd) {
                        oddTurn = true;  // Next should be odd number (1,3,5...)
                    } else {
                        evenTurn = true; // Next should be even number (2,4,6...)
                    }
                    
                    // Wake up all waiting threads - they'll check their turn flags
                    lock.notifyAll(); 
                    isOdd = !isOdd; // Alternate between odd and even for next iteration
                    
                    // Memory barrier: All changes flushed to main memory on exit
                } catch (InterruptedException e) {
                    System.out.println("Exception : " + e);
                }
            }
        }
    }

    /**
     * Odd thread - prints odd numbers (1,3,5...) when signaled by zero thread
     */
    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) { // Only odd numbers: 1,3,5,7...
            synchronized (lock) { // Same shared lock as other threads
                try {
                    // Wait until zero thread signals it's odd's turn
                    // Memory barrier: Fresh read of oddTurn from main memory
                    while (!oddTurn) {
                        lock.wait(); // Release lock and wait for zero's signal
                    }
                    
                    System.out.println(i); // Print the odd number
                    oddTurn = false; // Odd's turn is over
                    zeroTurn = true; // Give control back to zero thread
                    
                    // Wake up zero thread to continue the sequence
                    lock.notifyAll();
                    
                    // Memory barrier: Changes visible to other threads on exit
                } catch (InterruptedException e) {
                    System.out.println("Exception : " + e);
                }
            }
        }
    }

    /**
     * Even thread - prints even numbers (2,4,6...) when signaled by zero thread
     */
    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) { // Only even numbers: 2,4,6,8...
            synchronized (lock) { // Same shared lock ensures mutual exclusion
                try {
                    // Wait until zero thread signals it's even's turn
                    // Memory barrier: Fresh read of evenTurn from main memory
                    while (!evenTurn) {
                        lock.wait(); // Release lock and wait for zero's signal
                    }
                    
                    System.out.println(i); // Print the even number
                    evenTurn = false; // Even's turn is over
                    zeroTurn = true;  // Give control back to zero thread
                    
                    // Wake up zero thread to continue the sequence
                    lock.notifyAll();
                    
                    // Memory barrier: Changes visible to other threads on exit
                } catch (InterruptedException e) {
                    System.out.println("Exception : " + e);
                }
            }
        }
    }

    /**
     * Main method demonstrating the synchronized thread coordination
     * Expected output: 0 1 0 2 0 3 0 4 0 5
     */
    public static void main(String[] args) throws InterruptedException {
        ZeroOddEvenSynchronized sync = new ZeroOddEvenSynchronized(5);

        // Create three threads that will coordinate using shared lock
        Thread zero = new Thread(() -> {
            try {
                sync.zero(); // Prints zeros and controls sequence
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread even = new Thread(() -> {
            try {
                sync.even(); // Prints even numbers when signaled
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread odd = new Thread(() -> {
            try {
                sync.odd(); // Prints odd numbers when signaled
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Start all threads - they will coordinate via synchronized blocks
        zero.start();
        odd.start();
        even.start();

        // Wait for all threads to complete
        zero.join();
        even.join();
        odd.join();
    }
}