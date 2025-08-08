package Synchronization.SyncMethod;

public class Main {

    public static void main(String[] args) {
        SyncMethodCounter counter = new SyncMethodCounter();

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {

            threads[i] = new Thread(new Runnable() {
                public void run() {
                    counter.increment();
                }
            }, "Thread-" + (i + 1));

            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join(); // wait for all threads to finish before getting final counter value
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("Final counter value : " + counter.getCount());
    }
}
