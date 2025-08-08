package Semaphores.SemaphoreVsLock;

public class Main {
    public static void main(String[] args) {
        SemaphoreVsLockExample example = new SemaphoreVsLockExample();

        for (int i = 0; i < 5; i++) {
            final int threadNum = i + 1;

            Thread sT = new Thread(() -> example.accessWithSemaphore("Thread-" + threadNum));
            Thread lt = new Thread(() -> example.accessWithLock("Thread-" + threadNum));

            sT.start();
            lt.start();
        }
    }
}
