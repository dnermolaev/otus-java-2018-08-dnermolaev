package home;

public class Sequence {

    private static int first = 1;
    private static int second = 1;

    private synchronized void run(int number) {
        while (true) {
            sleep (1000);
            System.out.print(number + " ");
            if (number!=0)
                wait(this);
            number++;

            if (number == 10) {
                number = decrease(number);
            }
        }
    }

    int decrease(int number) {
        while (number > 1) {
            sleep(1_000);
            System.out.print(number + " ");
            number--;
            wait(this);
        }
        return number;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void wait(Object object) {
        try {
            object.wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sequence sequence = new Sequence();
        Thread t1 = new Thread(() -> {
            System.out.print("Thread 1: ");
            sequence.run(first);
        });
        Thread t2 = new Thread(() -> {
            System.out.println();
            System.out.print("Thread 2:  ");
            sequence.run(second);
        });

        t1.start();
        t1.join(2000);
        t2.start();
    }
}
