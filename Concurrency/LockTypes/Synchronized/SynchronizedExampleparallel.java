package LockTypes.Synchronized;

public class SynchronizedExampleparallel {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    // explicit object locking 

    public void longTask(String taskName) {
        synchronized(lock1) {
            System.out.println(taskName + " Acquired Lock1.");
            try {
                System.out.println(taskName + " Performing task.");
                Thread.sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(taskName + " Released Lock1.");
        }
    }

    public void longTask2(String taskName) {
        synchronized(lock2) {
            System.out.println(taskName + " Acquired Lock2.");
            System.out.println(taskName + " Performing task.");
            System.out.println(taskName + " Released Lock2.");
        }
    }
}