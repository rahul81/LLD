package LockTypes.Synchronized;

public class SynchronizedExample {

    public synchronized void longTask(String taskName) {
        System.out.println(taskName + " Accquired Lock.");
        try {
            System.out.println(taskName + " Performing task.");
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(taskName + " Released Lock.");
    }

    public synchronized void longTask2(String taskName) {
        System.out.println(taskName + " Accquired Lock.");
        System.out.println(taskName + "Performing task.");
        System.out.println(taskName + "Released Lock.");
    }

}
