package home;

public class Main {
    public static void main(String... args) throws InterruptedException {

        int size = 20_000_000;

        while (true) {
            long mem = getMem();
            System.out.println("Mem: " + mem);

            Object[] array = new Object[size];

            long mem2 = getMem();
            System.out.println("Reference size: " + (mem2 - mem) / array.length);

            for (int i = 0; i < array.length; i++) {
                array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]); // java8 or java9
                //array[i] = new String(new byte[0]); //without String pool
                //array[i] = new MyClass();
                //array[i] = new byte[1]; //16 or 24 with -XX:-UseCompressedOops
                //array[i] = getObject();
            }

            long mem3 = getMem();
            System.out.println("Class: " + array[0].getClass().getName() + " size: " + (mem3 - mem2) / array.length);

            array = null;

            Thread.sleep(1000); //wait for 1 sec
        }
    }

    private static Object getObject() {
        return new String();
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static class MyClass {
        private boolean b = true;
        private boolean b2 = true;// +1
        private boolean b3 = true;
        private boolean b4 = true;// +1
        private boolean b5 = true;// +1
        //private byte b2 = 0;
        //private byte b3 = 0;     // +1
        // private byte b4 = 0;
        //private byte b5 = 0;
        private int i = 0;      // +4
        //private long l = 1;     // +8
    }
}

