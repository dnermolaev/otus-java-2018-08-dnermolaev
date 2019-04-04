package sort;

import java.util.*;
import static sort.ComLineParser.*;

/**
 * @author dnermolaev
 */

public class MergeSortInt<T> implements Algorithm <T> {

    // сортировка слиянием для целочисленных значений
    public List sort(ArrayList<T> list, Boolean isAscending) throws NumberFormatException{
        int[] array = new int[list.size()];
        try{for (int i=0; i<array.length; i++) {
            array [i]= Integer.parseInt(String.valueOf(list.get(i)));
        }
        }catch (NumberFormatException e) {
            System.out.println("Incorrect type of data"+ exitMessage);
            exitByEnterPressed();
        }

        int[] buffer1 = Arrays.copyOf(array, array.length);
        int[] buffer2 = new int[array.length];
        int [] result = mergesortInner(buffer1, buffer2, 0, array.length);

        //сортировка по убыванию

        if (isAscending==false){
            int n = result.length;
            int temp;

            for (int i = 0; i < n/2; i++) {
                temp = result[n-i-1];
                result[n-i-1] = result[i];
                result[i] = temp;
            }
        }

        List<Integer> resultList=new ArrayList();
        for (int i:result){
            resultList.add(i);
        }

        return resultList;
    }

    public static int[] mergesortInner(int[] buffer1, int[] buffer2,
                                       int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergesortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergesortInner(buffer1, buffer2, middle, endIndex);

        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }

}
