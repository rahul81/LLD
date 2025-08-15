package ConcurrentCollections.Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueueExample {

    public static void main(String[] args) throws InterruptedException {

        // Queue using LinkedList

        Queue<Integer> LinkedListQueue = new LinkedList<>();

        // Create producer and consumer threads
        Thread producerLinkedList = new Thread(() -> {

            try {
                for (int i = 0; i < 1000; i++) {
                    LinkedListQueue.offer(i);
                    // Thread.sleep(1);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            System.out.println("Producer finished adding 1000 items to LinkedList");
        });

        Thread consumerLinkedList = new Thread(() -> {
            int count = 0;
            try {
                // Thread.sleep(5);
                while (count < 1000) {
                    Integer item = LinkedListQueue.poll();
                    // if (item != null) {
                    // System.out.println("item : " + item);
                    count++;

                    // }
                }
                System.out.println("Consumer processed " + count + " items from LinkedList");
            } catch (Exception e) {
                // TODO: handle exception
            }

        });

        // Start both threads
        producerLinkedList.start();
        consumerLinkedList.start();

        producerLinkedList.join();
        consumerLinkedList.join();
        System.out.println("Final size of LinkedListQueue (should be 0) : " + LinkedListQueue.size());
        // It won't be zero because of concurrent access while the Queue is being
        // updated
        System.out.println("Note: LinkedList might have unexpected behavior or exceptions in concurrent scenarios ⚠️");
        System.out.println();

        // ConcurrentLinkedListQueue in a multi-threaded env
        // Concurrent LinkedQueue uses Compare and set algorithm
        // Read more about Blocking and non blocking queues

        ConcurrentLinkedDeque<Integer> concurrentQueue = new ConcurrentLinkedDeque<>();

        // Multiple Producer threads

        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                concurrentQueue.offer(i);
            }
            System.out.println("Producer 1 finished adding 500 items ");
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                concurrentQueue.offer(i);
            }
            System.out.println("Producer 2 finished adding 500 items ");
        });

        // Multiple Consumer threads

        Thread consumer1 = new Thread(() -> {
            int count = 0;
            while (count < 500) {
                Integer item = concurrentQueue.poll();
                if (item != null) {
                    count++;
                }
            }
            System.out.println("Consumer 1 processed 500 items ");
        });

        Thread consumer2 = new Thread(() -> {
            int count = 0;
            while (count < 500) {
                Integer item = concurrentQueue.poll();
                if (item != null) {
                    count++;
                }
            }
            System.out.println("Consumer 2 processed 500 items ");
        });
        ;

        // Start all threads
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // Wait for all threads to finish
        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();

        System.out.println("ConcurrentLinkedQueue size after operation (should be 0) : " + concurrentQueue.size());

    }
}
