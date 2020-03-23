package bbm.sort;

import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
public class SorterTest {

    @Test
    public void testSort() {
        Sorter sorter = new HeapSort();
        int[] result = sorter.sort(new int[] {-4,0,7,4,9,-5,-1,0,-7,-1});
        sorter.print(result);
    }
}
