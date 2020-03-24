package bbm.sort;

import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
public class SorterTest {

    @Test
    public void testSort() {
        Sorter sorter = new BucketSorter();
        int[] result = sorter.sort(new int[] {-7087,12694,-19352,-7660,12052,-11316,-352,18321,15,19967,6331});
        sorter.print(result);
    }
}
