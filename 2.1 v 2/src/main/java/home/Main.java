package home;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        printObjectSize(new Object());
        //System.out.println("Object, size=" + MemoryCounter.instance().getInstrumentation().getObjectSize(new Object()));
        //printObjectSize(new A());
        printObjectSize(1);
        printObjectSize("string");
        //printObjectSize(Calendar.getInstance());
        //printObjectSize(new BigDecimal("999999999999999.999"));
        printObjectSize(new int[10]);
        printObjectSize(new ArrayList<String>());

        //printObjectSize(new Integer[100]);
    }

    public static void printObjectSize(Object obj) {
        System.out.println(String.format("%s, size=%s", obj.getClass()
                .getSimpleName(), MemoryCounter.getSize(obj)));
    }
}

