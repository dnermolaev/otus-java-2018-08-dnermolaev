package home;

import java.util.ArrayList;
import java.util.List;

class Benchmark extends Thread implements BenchmarkMBean  {
    private volatile int size = 0;

    List<Object> list = new ArrayList<Object>() ;

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {

        System.out.println("Starting the loop");

        while (true) {
            int local = size;
            Object[] array = new Object[local];

            System.out.println("Array of size: " + array.length + " created");

            for (int i = 0; i < local; i++) {
                array[i] = new String(new char[0]);
                list.add(array[i]);

            }
            System.out.println("Created " + local + " objects.");

            int a=0;
            a= list.size()-(local/2);
            for (int i=a; i<list.size();i++)
               list.remove(i);

            GC.printGCMetrics();
            System.out.println("Size: " + list.size());

            /*try {
                System.out.println("sleep 2");
                sleep(30000);
                size=10_000_000;
            } catch (InterruptedException e) {
            }*/

        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

}
