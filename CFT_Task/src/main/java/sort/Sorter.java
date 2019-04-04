package sort;

import java.util.*;

/**
 * @author dnermolaev
 */

public class Sorter<T> implements Algorithm<T> {
    private Algorithm<T> algorithm;

    public Sorter(Algorithm<T> algorithm) {
        this.algorithm = algorithm;
    }

    public void setAlgorithm(Algorithm<T> algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public List sort(ArrayList<T> list, Boolean orderType) {
        if (algorithm == null) {
            throw new IllegalStateException();
        }

        List outputList=new ArrayList();
        outputList=algorithm.sort(list, orderType);
        return outputList;
    }
}
