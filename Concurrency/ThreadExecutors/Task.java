package ThreadExecutors;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    private int id;

    Task(int id) {
        this.id = id;
    }

    @Override
    public String call() {

        try {
            Thread.sleep(1000);
            System.out.println("Thread " + Thread.currentThread().getName() + " running Task " + id);

        } catch (Exception e) {
            // TODO: handle exception
        }

        return "Task " + id;
    }

}
