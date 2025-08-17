package ConcurrencyProblems.FizzBuzz;

/**
 * Multi-threaded FizzBuzz using synchronized blocks and wait/notify
 * Four threads coordinate using shared state variables and a single lock
 * Demonstrates classic producer-consumer pattern with multiple consumers
 */
/**
 * Multi-threaded FizzBuzz using synchronized blocks and wait/notify
 * Four threads coordinate using shared state variables and a single lock
 */
public class FizzBuzzWithLocks {

    private int n;

    // Single shared lock for all thread coordination
    // All threads must use same lock for wait/notify to work
    // Single shared lock for all thread coordination
    private Object lock = new Object();
    
    // State variables control which thread can execute (no volatile needed - protected by synchronized)
    // Only one can be true at a time, ensuring mutual exclusion
    // State variables control which thread can execute (no volatile needed - protected by synchronized)
    private boolean isFizzTurn = false;      // Fizz thread can execute      // Fizz thread can execute
    private boolean isBuzzTurn = false;      // Buzz thread can execute      // Buzz thread can execute
    private boolean isFizzBuzzTurn = false;  // FizzBuzz thread can execute  // FizzBuzz thread can execute
    private boolean isPrinNumberTurn = true; // Number thread starts first // Number thread starts first

    FizzBuzzWithLocks(int n) {
        this.n = n;
    }

    /**
     * Number thread - acts as controller, decides which specialized thread should execute
     * Uses shared state variables to coordinate with other threads
     */
    /**
     * Number thread - acts as controller, decides which specialized thread should execute
     */
    public void printNumber() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (lock) { // Acquire shared lock for thread safety
                // Wait until it's number thread's turn (prevents spurious wakeups)
                while (!isPrinNumberTurn) {
                    lock.wait(); // Release lock and wait for notification
                }

                // Determine which thread should handle this number
                // Priority: FizzBuzz (15) > Fizz (3) > Buzz (5) > Number
                if (i % 3 == 0 && i % 5 == 0) {
                    // Multiple of both 3 and 5 -> delegate to FizzBuzz thread
                    isFizzBuzzTurn = true;
                    isPrinNumberTurn = false; // Number thread's turn is over
                } else if (i % 3 == 0) {
                    // Multiple of 3 only -> delegate to Fizz thread
                    isFizzTurn = true;
                    isPrinNumberTurn = false; // Number thread's turn is over
                } else if (i % 5 == 0) {
                    // Multiple of 5 only -> delegate to Buzz thread
                    isBuzzTurn = true;
                    isPrinNumberTurn = false; // Number thread's turn is over
                } else {
                    // Regular number -> print directly (no delegation needed)
                    System.out.println(i);
                    // isPrinNumberTurn remains true for next iteration
                }
                
                // Wake up all waiting threads to check their turn flags
                lock.notifyAll(); // Memory barrier: all changes visible to other threads
            }
        }
    }

    /**
     * Fizz thread - handles multiples of 3 that are NOT multiples of 15
     * Waits for number thread to signal, prints "Fizz", gives control back
     */
    /**
     * Fizz thread - handles multiples of 3 that are NOT multiples of 15
     */
    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) { // Only multiples of 3: 3, 6, 9, 12...
            if (i % 5 == 0) {
                // Skip multiples of 15 (handled by fizzbuzz thread)
                continue;
            }

            synchronized (lock) { // Same shared lock as other threads
                // Wait until number thread signals it's fizz's turn
                while (!isFizzTurn) {
                    lock.wait(); // Release lock and wait for number thread's signal
                }

                System.out.println("Fizz");
                isFizzTurn = false;      // Fizz's turn is over
                isPrinNumberTurn = true; // Give control back to number thread

                // Wake up number thread to continue sequence
                lock.notifyAll(); // Memory barrier: changes visible to other threads
            }
        }
    }

    /**
     * Buzz thread - handles multiples of 5 that are NOT multiples of 15
     * Waits for number thread to signal, prints "Buzz", gives control back
     */
    /**
     * Buzz thread - handles multiples of 5 that are NOT multiples of 15
     */
    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) { // Only multiples of 5: 5, 10, 15, 20...
            if (i % 3 == 0) {
                // Skip multiples of 15 (handled by fizzbuzz thread)
                continue;
            }

            synchronized (lock) { // Same shared lock ensures mutual exclusion
                // Wait until number thread signals it's buzz's turn
                while (!isBuzzTurn) {
                    lock.wait(); // Release lock and wait for number thread's signal
                }
                
                System.out.println("Buzz");
                isBuzzTurn = false;      // Buzz's turn is over
                isPrinNumberTurn = true; // Give control back to number thread
                
                // Wake up number thread to continue sequence
                lock.notifyAll(); // Memory barrier: changes visible to other threads
            }
        }
    }

    /**
     * FizzBuzz thread - handles multiples of both 3 and 5 (i.e., multiples of 15)
     * Waits for number thread to signal, prints "FizzBuzz", gives control back
     */
    /**
     * FizzBuzz thread - handles multiples of both 3 and 5 (multiples of 15)
     */
    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) { // Only multiples of 15: 15, 30, 45...
            synchronized (lock) { // Same shared lock for coordination
                // Wait until number thread signals it's fizzbuzz's turn
                while (!isFizzBuzzTurn) {
                    lock.wait(); // Release lock and wait for number thread's signal
                }
                
                System.out.println("FizzBuzz");
                isFizzBuzzTurn = false;  // FizzBuzz's turn is over
                isPrinNumberTurn = true; // Give control back to number thread
                
                // Wake up number thread to continue sequence
                lock.notifyAll(); // Memory barrier: changes visible to other threads
            }
        }
    }

    /**
     * Main method demonstrating synchronized-based FizzBuzz coordination
     * 
     * Key differences from semaphore approach:
     * 1. SHARED STATE VARIABLES - boolean flags control execution
     * 2. SINGLE LOCK - all threads use same monitor for wait/notify
     * 3. BROADCAST SIGNALING - notifyAll() wakes all threads (less efficient)
     * 4. MORE COMPLEX STATE MANAGEMENT - multiple boolean flags to coordinate
     * 
     * Execution flow:
     * 1. Number thread starts (isPrinNumberTurn = true)
     * 2. Number thread checks divisibility, sets appropriate flag, calls notifyAll()
     * 3. Specialized thread wakes up, checks its flag, executes if true
     * 4. Specialized thread resets its flag, sets isPrinNumberTurn = true, calls notifyAll()
     * 5. Number thread wakes up and continues
     * 
     * Expected output for n=15:
     * 1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz
     */
    public static void main(String[] args) throws InterruptedException {
        FizzBuzzWithLocks fizzBuzz = new FizzBuzzWithLocks(15);

        // Specialized worker threads - each handles specific cases
        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizz(); // Handles multiples of 3 (not 15)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-fizz");

        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzz(); // Handles multiples of 5 (not 15)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-buzz");

        Thread fizzBuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(); // Handles multiples of 15
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-fizzBuzz");

        // Controller thread - manages sequence and delegates work
        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.printNumber(); // Controls entire sequence
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-printNumber");

        // Start all threads - they coordinate via shared lock and state variables
        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();

        // Wait for all threads to complete
        fizzThread.join();
        buzzThread.join();
        fizzBuzzThread.join();
        numberThread.join();
    }
}
