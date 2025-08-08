package Threads;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    String name;

    public MyCallable(String name) {
        this.name = name;
    }

    // This can have a return type
    @Override
    public String call() throws InterruptedException {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            result.append("Running ").append(name).append(" count ")
                    .append(i).append(" on Thread ")
                    .append(Thread.currentThread().getName())
                    .append("\n");
            Thread.sleep(500);
        }

        return result.toString();

    }

}
