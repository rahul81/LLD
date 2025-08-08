package Threads;

public class Count {

    public static void runCount(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Count " + i + " on Thread " + Thread.currentThread().getName());

            try {
                Thread.sleep(500); // millisec
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted : " + e);
            }
        }
    }

}
