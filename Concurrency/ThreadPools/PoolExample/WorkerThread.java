package ThreadPools.PoolExample;

public class WorkerThread implements Runnable {

    private final int taskId;

    WorkerThread(int id) {
        taskId = id;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is processing task : " + taskId);

        try {

            Thread.sleep(2000); // simulate task
        } catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("Task Thread interrupt " + e.getMessage());
        }

        System.out.println(Thread.currentThread().getName() + " finished task  " + taskId);

    }
}
