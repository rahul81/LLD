package Threads;

public class MyThread extends Thread {

    @Override
    public void run() {
        Count.runCount(5);
    }

    // this class is extending thread class hence cannot extend any other class
    // to solve this issue Runnable interface is used
}
