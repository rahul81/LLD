package Threads;

public class SleepExample {

    // On synchronized resources only one thread can take a lock
    // other Thread have to wait for Thread.sleep() to end, so that resource is free and then it can use it.
    public synchronized void sleepExample() {

        System.out.println("Thread " + Thread.currentThread().getName() + " is going to sleep !");

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(Thread.currentThread().getName()+ " woke up from sleep.");

    }

    public static void main(String args[]) {

        SleepExample shared = new SleepExample();

        Thread sleepThread1 = new Thread(() -> shared.sleepExample(), "SleeperThread 1");
        Thread sleepThread2 = new Thread(()-> shared.sleepExample(), "SleeperThread 2");

        sleepThread1.start();
        sleepThread2.start();
    }

}
