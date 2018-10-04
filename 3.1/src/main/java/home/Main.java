package home;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("moon");

        /*List <String> str = new ArrayList<String>();
        str.add("lol");
        str.add("cat");
        str.add("dog");**/

        String [] str = {"lol", "cat", "dog"};

        Collections.addAll(myList,str );
        for (int i = 0; i<myList.size; i++)
            System.out.println(myList.get(i));

        //for (int i = 0; i<((MyArrayList<String>) myList).size; i++)
         //   System.out.println(myList.get(i));

        System.out.println();

        Collections.sort(myList, String::compareTo);
        //MyArrayList.sort(myList);
        for (int i = 0; i<((MyArrayList<String>) myList).size; i++)
            System.out.println(myList.get(i));

        System.out.println();
        System.out.println("copy");
        MyArrayList<String> myList2 = new MyArrayList<>();
        myList2.add("1");
        myList2.add("2");
        myList2.add("3");
        myList2.add("4");
        myList2.add("5");
        Collections.copy(myList2,myList);
        for (int i=0; i<myList2.size;i++)
            System.out.println(myList2.get(i));


    }
}

