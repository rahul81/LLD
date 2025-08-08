package ThreadCommunication;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private final Queue<Integer> buffer = new LinkedList<Integer>();

    private final int CAPACITY = 5;

    private Object lock = new Object();

    private int value = 0;

    public void produce() throws InterruptedException {

        while (true) { // Outer loop to keep producing values
            synchronized (lock) {
                while (buffer.size() == CAPACITY) {
                    System.out.println("Buffer Queue is full Producer is waiting...");
                    lock.wait();
                    Thread.sleep(1000); // Check every second
                }

                System.out.println(Thread.currentThread().getName() + " : Producer produced value : " + value);
                buffer.offer(value++);

                // Notify All waiting threads that new value is available
                lock.notifyAll();
            }

            // Simulate produce delay
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {

        while (true) {
            synchronized (lock) {
                while (buffer.isEmpty()) {
                    System.out.println("Queue buffer is Empty, Consumer is waiting...");
                    lock.wait();
                }

                int value = buffer.poll();
                System.out.println("Consumer consumed value : " + value);
                lock.notifyAll();
            }

            // Consume delay
            Thread.sleep(800);
        }

    }
}
