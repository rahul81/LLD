package Synchronization.Volatile;

public class Main {

    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();

        Thread workerThread = new Thread(new Runnable() {
            public void run() {
                example.runTask(); // Start the counter in a thread.
            }
        }, "workerThread");

        workerThread.start();

        try {
            Thread.sleep(2000); // The worker thread run for sometime
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("MainThread: Stopping worker Thread");
        example.stopTask();

        try {
            workerThread.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("MainThread : Execution Finished.");

    }
}
