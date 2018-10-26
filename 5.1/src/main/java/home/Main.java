package home;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

// -Xloggc:gc.log -verbose:gc -XX:+PrintGCDetails

public class Main {

    public static void main(String... args) throws Exception {

        int size = 5 * 1000 * 100;

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setSize(size);
        mbean.run();

        //mbean.start();


        Thread.sleep(1000);
        Class<?> clazz = Object[].class;
    }

}
