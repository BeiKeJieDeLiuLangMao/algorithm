package bbm.sort;

import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
public class SorterTest {

    @Test
    public void testSort() {
        Sorter sorter = new CountingSorter();
        int[] result = sorter.sort(new int[] {5,2,3,1});
        sorter.print(result);
    }
}
