package home;

public class Sequence {

    private static int first = 0;
    private static int second = 0;
    private boolean last = true;
    private static int x = 1;
    private static int y = 1;

    private synchronized void run(int number, boolean flag) {
        while (true) {
            if (last == flag) {
                wait(this);

            } else {
                if (number == 10) {
                    number = decrease(number, flag);
                }
                number++;
                System.out.print(number + " ");
                last = flag;
                sleep(1_000);
                notify();
            }
        }
    }

    int decrease(int number, boolean flag) {
        while (number >= 1) {
            if (last == flag) {
                wait(this);
            } else {
                number--;
                if (number == 1) {
                    number--;
                    last = flag;
                    notify();
                } else {
                    System.out.print(number + " ");
                    last = flag;
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
            System.out.print("Thread 1: ");
            sequence.run(first, false);
        });
        Thread t2 = new Thread(() -> {
            System.out.println();
            System.out.println("Thread 2:  ");
            sequence.run(second, true);
        });

        t1.start();
        t1.join(1000);
        t2.start();
    }
}
