package ThreadCommunication;

public class Main {
    public static void main(String[] args) {

        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    pc.produce();
                } catch (Exception e) {
                    System.out.println("Producer Exception : " + e.getMessage());
                }
            }
        }, "ProducerThread");

        Thread producerThread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    pc.produce();
                } catch (Exception e) {
                    System.out.println("Producer Exception : " + e.getMessage());
                }
            }
        }, "ProducerThread2");

        Thread consumerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    pc.consume();
                } catch (Exception e) {
                    System.out.println("Consumer Exception : " + e.getMessage());
                }
            }
        }, "ConsumerThread");

        producerThread.start();
        producerThread2.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
