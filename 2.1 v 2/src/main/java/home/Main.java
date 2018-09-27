package home;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

    public int a;
    public String str=null;
    public String str1;
    public int [] array;
    public int [] array1;
    public ArrayList <String> list;
    public ArrayList <String> list1;


    public Main() {
        a = 5;
        str = "";
        str1="String + String + String";
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list1.add("Sun");
        list1.add("Moon");
        list1.add("Yellow");
        array = new int [5];
        array1 = new int [1000];
    }

    public static void main(String[] args) {

        System.out.println("Object, size=" + MemoryCounter.instance().getInstrumentation().getObjectSize(new Object()));

        System.out.println("Empty string, size=" + MemoryCounter.instance().getInstrumentation().getObjectSize(""));
        printObjectSize(1);
        //printObjectSize(Calendar.getInstance());
        //printObjectSize(new BigDecimal("999999999999999.999"));
        //printObjectSize(new int[10]);
        System.out.println("int[10], size=" + MemoryCounter.instance().getInstrumentation().getObjectSize(new int[10]));
        System.out.println("int[100], size=" + MemoryCounter.instance().getInstrumentation().getObjectSize(new int[100]));
        System.out.println("int[1000], size=" + MemoryCounter.instance().getInstrumentation().getObjectSize(new int[1000]));
        //printObjectSize(new ArrayList<String>());
        System.out.println("List <String>Empty, size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(new ArrayList<String>()));
        ArrayList<String> list = new ArrayList<>();
        list.add("Lena");
        list.add("Dima");
        list.add("cat");
        System.out.println("List <String> 3, size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(list));
        System.out.println();
        //printObjectSize(example.array);

        Main example = new Main();

        Class<?> cls = example.getClass();
        Field[] fields = cls.getFields();
        try {
            for (Field field : fields) {
                System.out.println("Class name : " + field.getName());
                System.out.println("Class type : " + field.getType().getName());
                //Field fld1 = cls.getField(field.getName());
                Object value = (Object) cls.getField(field.getName()).get(example);
                printObjectSize(value);
            }
        }
        catch (NoSuchFieldException | IllegalAccessException nsfe) {
            throw new RuntimeException(nsfe);
        }
    }

        /*Main example = null;
        try {
            Class clazz = Class.forName(Main.class.getName());
            example = (Main) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        printObjectSize(example);
        printObjectSize(example.a);
        System.out.println("Empty string, size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(example.str));
        //printObjectSize(example.str);
        System.out.println("Not empty string, size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(example.str1));
        //printObjectSize(example.str1);
        System.out.println("List <String>Empty, size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(example.list));
        //printObjectSize(example.list);
        System.out.println("List <String> 3, size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(example.list1));
        //printObjectSize(example.list1);
        System.out.println("int[5], size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(example.array));
        //printObjectSize(example.array);
        System.out.println("int[1000], size=" + MemoryCounter.instance().getInstrumentation()
                .getObjectSize(example.array1));
        //printObjectSize(example.array1);*/

    public static void printObjectSize(Object obj) {
        System.out.println(String.format("%s, size=%s", obj.getClass()
                .getSimpleName(), MemoryCounter.getSize(obj)));
    }
}

