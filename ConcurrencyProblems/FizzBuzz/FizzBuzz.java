package ConcurrencyProblems.FizzBuzz;

import java.util.concurrent.Semaphore;

/**
 * Multi-threaded FizzBuzz using Semaphores for thread coordination
 * Four threads coordinate to print numbers 1 to n with FizzBuzz rules:
 * - Multiples of 3: "Fizz"
 * - Multiples of 5: "Buzz" 
 * - Multiples of 15: "FizzBuzz"
 * - Other numbers: the number itself
 */
public class FizzBuzz {

    private int n;
    
    // Four semaphores control which thread can execute at any time
    // Cross-semaphore signaling enables coordination between different threads
    private Semaphore fizzSemaphore = new Semaphore(0);     // Controls fizz thread (multiples of 3 only)
    private Semaphore buzzSemaphore = new Semaphore(0);     // Controls buzz thread (multiples of 5 only)
    private Semaphore fizzBuzzSemaphore = new Semaphore(0); // Controls fizzBuzz thread (multiples of 15)
    private Semaphore numberSemaphore = new Semaphore(1);   // Controls number thread (starts first)

    public FizzBuzz(int n) {
        this.n = n;
    }

    /**
     * Number thread - controls the sequence and delegates to appropriate threads
     * Acts as the "controller" that decides which thread should execute next
     */
    public void printNumber() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            // Wait for permission to check the current number
            numberSemaphore.acquire(); // Blocks until permit available

            // Determine which thread should handle this number
            // Priority: FizzBuzz (15) > Fizz (3) > Buzz (5) > Number
            if (i % 3 == 0 && i % 5 == 0) {
                // Multiple of both 3 and 5 (15, 30, 45...) -> FizzBuzz thread
                fizzBuzzSemaphore.release(); // Give permission to fizzBuzz thread
            } else if (i % 3 == 0) {
                // Multiple of 3 only (3, 6, 9, 12...) -> Fizz thread
                fizzSemaphore.release(); // Give permission to fizz thread
            } else if (i % 5 == 0) {
                // Multiple of 5 only (5, 10, 20, 25...) -> Buzz thread
                buzzSemaphore.release(); // Give permission to buzz thread
            } else {
                // Regular number (1, 2, 4, 7, 8...) -> Print directly
                System.out.println(i);
                numberSemaphore.release(); // Give permission back to self for next iteration
            }
        }
    }

    /**
     * Fizz thread - handles multiples of 3 that are NOT multiples of 15
     * Waits for permission from number thread, prints "Fizz", gives control back
     */
    public void fizz() {
        for (int i = 3; i <= n; i += 3) { // Only multiples of 3: 3, 6, 9, 12...
            if (i % 5 == 0) {
                // Skip multiples of 15 (handled by fizzBuzz thread)
                continue;
            }
            try {
                // Wait for number thread to give permission
                fizzSemaphore.acquire(); // Blocks until number thread releases permit
                System.out.println("Fizz");
                // Give control back to number thread for next iteration
                numberSemaphore.release(); // Cross-semaphore signaling
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Buzz thread - handles multiples of 5 that are NOT multiples of 15
     * Waits for permission from number thread, prints "Buzz", gives control back
     */
    public void buzz() {
        for (int i = 5; i <= n; i += 5) { // Only multiples of 5: 5, 10, 15, 20...
            if (i % 3 == 0) {
                // Skip multiples of 15 (handled by fizzBuzz thread)
                continue;
            }
            try {
                // Wait for number thread to give permission
                buzzSemaphore.acquire(); // Blocks until number thread releases permit
                System.out.println("Buzz");
                // Give control back to number thread for next iteration
                numberSemaphore.release(); // Cross-semaphore signaling
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * FizzBuzz thread - handles multiples of both 3 and 5 (i.e., multiples of 15)
     * Waits for permission from number thread, prints "FizzBuzz", gives control back
     */
    public void fizzBuzz() {
        for (int i = 15; i <= n; i += 15) { // Only multiples of 15: 15, 30, 45...
            try {
                // Wait for number thread to give permission
                fizzBuzzSemaphore.acquire(); // Blocks until number thread releases permit
                System.out.println("FizzBuzz");
                // Give control back to number thread for next iteration
                numberSemaphore.release(); // Cross-semaphore signaling
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Main method demonstrating multi-threaded FizzBuzz coordination
     * 
     * Execution flow:
     * 1. Number thread starts (has initial permit)
     * 2. For each i from 1 to n:
     *    - Number thread checks divisibility rules
     *    - Releases permit to appropriate specialized thread
     *    - Specialized thread prints and gives control back to number thread
     * 
     * Expected output for n=15:
     * 1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz
     */
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        // Controller thread - manages the sequence and delegates work
        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.printNumber(); // Controls the entire sequence
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Specialized worker threads - each handles specific cases
        Thread fizzThread = new Thread(() -> {
            fizzBuzz.fizz(); // Handles multiples of 3 (not 15)
        });

        Thread buzzThread = new Thread(() -> {
            fizzBuzz.buzz(); // Handles multiples of 5 (not 15)
        });

        Thread fizzBuzzThread = new Thread(() -> {
            fizzBuzz.fizzBuzz(); // Handles multiples of 15
        });

        // Start all threads - they coordinate via semaphore permits
        numberThread.start();
        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();

        try {
            // Wait for all threads to complete
            numberThread.join();
            fizzThread.join();
            buzzThread.join();
            fizzBuzzThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
