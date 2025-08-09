package Semaphores.ReadWriteLock;

public class Main {
    public static void main(String[] args) {

        ReaderWriterLock rw = new ReaderWriterLock();

        Thread t1 = new Thread(() -> {
            try {
                rw.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "reader-1");

        Thread t2 = new Thread(() -> {
            try {
                rw.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "reader-2");

        Thread t3 = new Thread(() -> {
            try {
                rw.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "writer-1");

        t1.start();
        t2.start();
        t3.start();
        

    }
}
