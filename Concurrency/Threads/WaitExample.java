package Threads;

public class WaitExample {

    public synchronized void waitExample() {

        System.out.println("Thread " + Thread.currentThread().getName() + " is entering waiting.");

        try {
            wait();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " has resumed after waiting.");

    }

    public synchronized void notifyExample() {
        System.out.println("Notifying a wating thread...");
        notify(); // ends the waiting for one thread
    }

    public static void main(String args[]) {

        WaitExample shared = new WaitExample();

        Thread waitThread1 = new Thread(() -> shared.waitExample(), "WaitThread 1");
        Thread waitThread2 = new Thread( () -> shared.waitExample(), "WaitThread 2");
        Thread notifier = new Thread(() -> {
            try {
                Thread.sleep(2000); // sleep to make sure threads  calls waitExample so it enters wait state.
                shared.notifyExample(); // resume one thread 
                Thread.sleep(2000); // wait before resuming another thread
                shared.notifyExample();
            } catch (Exception e) {
                // TODO: handle exception
            }

        }, "notifier");

        waitThread1.start();
        waitThread2.start();
        notifier.start();

        // unlike sleep which waits does not enter the function unless Thread.sleep() ends
        // wait frees the resource hence thread 2 can also enter and print the waiting statement.

    }

}
