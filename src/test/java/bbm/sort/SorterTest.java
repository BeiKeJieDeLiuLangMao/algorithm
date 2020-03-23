package bbm.sort;

import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
public class SorterTest {

    @Test
    public void testSort() {
        Sorter sorter = new QuickSorter();
        int[] result = sorter.sort(new int[] {5,1,0,0,1,2});
        sorter.print(result);
    }
}
