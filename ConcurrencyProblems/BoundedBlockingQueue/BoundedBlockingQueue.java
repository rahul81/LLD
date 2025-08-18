package ConcurrencyProblems.BoundedBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBlockingQueue {

    private final Queue<Integer> queue = new LinkedList<Integer>();
    private final int CAPACITY;

    private Object lock = new Object();

    public BoundedBlockingQueue(int n) {
        this.CAPACITY = n;

    }

    public int size() {
        return queue.size();
    }

    public void enqueue(int element) throws InterruptedException {

        synchronized (lock) {
            while (queue.size() == CAPACITY) {
                System.out.println("Queue is full, waiting for consumer to consume...");
                lock.wait();
            }
            queue.offer(element);
            System.out.println("Enqueued: " + element);
            lock.notifyAll();
        }

    }

    public void dequeue() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty, waiting new elements to be available in queue...");
                lock.wait();

            }
            int element = queue.poll();
            System.out.println("Dequeued: " + element);
            lock.notifyAll();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(2); // capacity = 2

        Thread producer1 = new Thread(() -> {
            try {
                System.out.println("Producer1: enqueue(1)");
                queue.enqueue(1);
                
                Thread.sleep(1500); // Wait for consumer activity
                
                System.out.println("Producer1: enqueue(2)");
                queue.enqueue(2);

                Thread.sleep(300);
                
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

                Thread.sleep(300);
                
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
