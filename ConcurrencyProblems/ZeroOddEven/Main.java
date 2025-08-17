package ConcurrencyProblems.ZeroOddEven;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ZeroOddEven zeroOddEven = new ZeroOddEven(5);
        Thread zero = new Thread(() -> {
            try {
                zeroOddEven.zero(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread even = new Thread(() -> {
            try {
                zeroOddEven.even(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread odd = new Thread(() -> {
            try {
                zeroOddEven.odd(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        zero.start();
        odd.start();
        even.start();

        zero.join();
        even.join();
        odd.join();
    }
}
