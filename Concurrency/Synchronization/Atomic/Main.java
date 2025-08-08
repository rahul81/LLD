package Synchronization.Atomic;

public class Main {
    public static void main(String[] args) {

        AtomicCounter Counter = new AtomicCounter();

        int numOfThreads = 5;
        Thread[] threads = new Thread[numOfThreads];

        int incPerThread = 100; // increments per thread

        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                
                public void run() {

                    for (int i = 0; i < incPerThread; i++) {
                        Counter.increment();
                    }
                }
            }, "Thread-" + (i + 1));

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        System.out.println("Final counter value : " + Counter.getCount());

    }
}
