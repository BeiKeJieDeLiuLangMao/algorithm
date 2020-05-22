package bbm.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author bbm
 * @date 2020/5/20
 */
public class Utils {
    public static void print(int[] array) {
        if (array != null) {
            Arrays.stream(array).forEach(t -> System.out.print(t + " "));
        }
        System.out.println();
    }

    public static void print(char[] array) {
        if (array != null) {
            for (char c : array) {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }

    public static <T> void print(ValueIterator it) {
        System.out.print(it.getValue() + " ");
        while (it.hasNext()) {
            it = it.next();
            System.out.print(it.getValue() + " ");
        }
        System.out.println();
    }

    public static void print(Collection e) {
        System.out.println();
        e.forEach(item -> {
            if (item instanceof Collection) {
                print((Collection) item);
            } else {
                System.out.print(item.toString() + " ");
            }
        });
        System.out.println();
    }

    public interface ValueIterator extends Iterator<ValueIterator> {
        Object getValue();
    }
}
