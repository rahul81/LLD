package Semaphores.BarrierPattern;

import java.util.concurrent.Semaphore;

public class SemaphoreBarrierPattern {

    public static class SemaphoreBarrier {
        private final int parties;
        private int count = 0;
        private final Semaphore mutex = new Semaphore(1);
        private final Semaphore barrier = new Semaphore(0);

        public SemaphoreBarrier(int parties) {
            this.parties = parties;
            this.count = parties;
        }

        public void await() throws InterruptedException {
            mutex.acquire();
            count--;
            if (count == 0) {
                barrier.release(parties - 1);
                count = parties;
                mutex.release();
            } else {
                // release mutex so other threads can update the count 
                mutex.release();
                // wait until the last thread releases this thread
                barrier.acquire();
            }
        }
    }
}
