package home;

public class Sequence {

    private static int first = 0;
    private static int second = 0;
    private String last = "Thread 2";

    private synchronized void run(int number) {
        while (true) {
            if (last.equals(Thread.currentThread().getName())) {
                wait(this);

            } else {
                if (number == 10) {
                    number = decrease(number);
                }
                number++;
                System.out.print(number + " ");
                last = Thread.currentThread().getName();
                sleep(1_000);
                notify();
            }
        }
    }

    int decrease(int number) {
        while (number >= 1) {
            if (last.equals(Thread.currentThread().getName())) {
                wait(this);
            } else {
                number--;
                if (number == 1) {
                    number--;
                    last =Thread.currentThread().getName();
                    notify();
                } else {
                    System.out.print(number + " ");
                    last =Thread.currentThread().getName();
                    sleep(1_000);
                    notify();
                }
            }
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
            object.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sequence sequence = new Sequence();
        Thread t1 = new Thread(() -> {
            sequence.run(first);
        });
        t1.setName("Thread 1");
        Thread t2 = new Thread(() -> {
            sequence.run(second);
        });
        t2.setName("Thread 2");

        System.out.print(t1.getName()+ " ");
        t1.start();
        sleep(1000);
        System.out.println();
        System.out.print(t2.getName()+"  ");
        t2.start();
    }
}
