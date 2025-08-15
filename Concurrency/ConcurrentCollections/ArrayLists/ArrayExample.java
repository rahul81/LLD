package ConcurrentCollections.ArrayLists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayExample {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Thread arrayListUpdater = new Thread(() -> {
            try {
                Thread.sleep(50);
                for (int i = 4; i <= 6; i++) {
                    System.out.println("ArrayList writer adding : " + i);
                    arrayList.add(i);
                    Thread.sleep(50);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread arrayListReader = new Thread(() -> {
            try {
                Thread.sleep(25); // Wait a bit so that some elements are added
                // This loop is likely to throw ConcurrentModificationException
                // because the list is modified during iteration.

                for (Integer element : arrayList) {
                    System.out.println("arrayListReader: " + element);
                    Thread.sleep(50);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        arrayListUpdater.start();
        arrayListReader.start();
        arrayListReader.join();
        arrayListUpdater.join();

        System.out.println("Final Array List : " + arrayList);

        List<Integer> concurrentArray = new CopyOnWriteArrayList<>();
        concurrentArray.add(1);
        concurrentArray.add(2);
        concurrentArray.add(3);

        Thread concurrentArrayUpdater = new Thread(() -> {
            try {
                for (int i = 4; i <= 6; i++) {
                    System.out.println("CopyOnWriteArrayList adding : " + i);
                    concurrentArray.add(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread concurrentArrayReader = new Thread(() -> {
            try {
                Thread.sleep(25); // Wait a bit so that some elements are added
                for (Integer element : concurrentArray) {
                    System.out.println("CopyOnWriteArrayList Reader read : " + element);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        concurrentArrayUpdater.start();
        concurrentArrayReader.start();
        concurrentArrayReader.join();
        concurrentArrayUpdater.join();

        System.out.println("Final CopyOnWrite ArrayList : " + concurrentArray);

    }
}
