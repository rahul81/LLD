package ConcurrencyProblems.BoundedBlockingQueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class BlockingQueueWithSemaphores {

    private final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private final int CAPACITY;

    private Semaphore full;
    private Semaphore empty;

    BlockingQueueWithSemaphores(int capacity) {
        this.CAPACITY = capacity;
        this.full = new Semaphore(0);
        this.empty = new Semaphore(this.CAPACITY);
    }

    public void enqueue(int element) throws InterruptedException {
        empty.acquire();
        queue.offer(element);
        System.out.println("Enqueued: " + element);
        full.release();
    }

    public void dequeue() throws InterruptedException {
        full.acquire();
        int element = queue.poll();
        System.out.println("Dequeued: " + element);
        empty.release();
    }

    public int size() throws InterruptedException {
        int result = 0;
        result = queue.size();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueWithSemaphores queue = new BlockingQueueWithSemaphores(2); // capacity = 2

        Thread producer1 = new Thread(() -> {
            try {
                System.out.println("Producer1: enqueue(1)");
                queue.enqueue(1);
                
                Thread.sleep(1500); // Wait for consumer activity
                
                System.out.println("Producer1: enqueue(2)");
                queue.enqueue(2);
                
                System.out.println("Producer1: enqueue(4) - may block if queue full");
                queue.enqueue(4);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer1");

        Thread producer2 = new Thread(() -> {
            try {
                Thread.sleep(1200); // Start after producer1
                
                System.out.println("Producer2: enqueue(0)");
                queue.enqueue(0);
                
                System.out.println("Producer2: enqueue(3) - may block if queue full");
                queue.enqueue(3);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer2");

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(500); // Let producer1 enqueue first item
                
                System.out.println("Consumer: dequeue() - returns first item");
                queue.dequeue();
                
                System.out.println("Consumer: dequeue() - will block if queue empty");
                queue.dequeue();
                
                Thread.sleep(2000); // Let producers compete for queue space
                
                System.out.println("Consumer: dequeue() - unblocks waiting producers");
                queue.dequeue();
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer1.start();
        producer2.start();
        consumer.start();
        
        producer1.join();
        producer2.join();
        consumer.join();
        
        System.out.println("Final queue size: " + queue.size());
    }
}
