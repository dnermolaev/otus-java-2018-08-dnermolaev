package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dnermolaev
 */

public class MergeSortString<T> implements Algorithm<T> {

    // сортировка слиянием для строковых значений
    public List sort(ArrayList<T> list, Boolean isAscending) throws NumberFormatException {
        String[] array = new String [list.size()];
        list.toArray(array);

        String[] buffer1 = Arrays.copyOf(array, array.length);
        String[] buffer2 = new String[array.length];
        String[] result = mergesortInner(buffer1, buffer2, 0, array.length);

        //сортировка по убыввнию
        if (isAscending==false) {
            int n = result.length;
            String temp;

            for (int i = 0; i < n/2; i++) {
                temp = result[n-i-1];
                result[n-i-1] = result[i];
                result[i] = temp;
            }
        }

        List<String> resultList=new ArrayList();
        for (String str:result){
            resultList.add(str);
        }

        return resultList;
    }

    public static String[] mergesortInner(String[] buffer1, String[] buffer2,
                                          int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        String[] sorted1 = mergesortInner(buffer1, buffer2, startIndex, middle);
        String[] sorted2 = mergesortInner(buffer1, buffer2, middle, endIndex);

        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        String[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = (sorted1[index1].compareTo(sorted2[index2])<0)? sorted1[index1++] : sorted2[index2++];
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
