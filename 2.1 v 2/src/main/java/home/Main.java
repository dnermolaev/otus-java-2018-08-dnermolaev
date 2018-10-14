package home;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

    public int a;
    //public byte b;
    public String str=null;
    public String str1;
    public int [] array = {1,2,3, 4 ,4};
    public int [] array1;
    public ArrayList <String> list;
    public ArrayList <String> list1;


    public Main() {
        int a=5;
        //b = 3;
        //str = "";
        //str1="String + String + String";
        //list = new ArrayList<>();
        //list1 = new ArrayList<>();
        //list1.add("Sun");
        //list1.add("Moon");
        //list1.add("Yellow");
        int [] array;
        array1 = new int [1000];
    }

    public static void main(String[] args) {

        Main example = new Main();

        MemoryCounter.printObjectSize(example);
    }




}

