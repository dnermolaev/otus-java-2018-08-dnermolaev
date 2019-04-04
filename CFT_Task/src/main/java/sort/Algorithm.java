package sort;

import java.util.*;

/**
 * @author dnermolaev
 */

public interface Algorithm <T> {
    List sort(ArrayList<T> list, Boolean orderType);
}
