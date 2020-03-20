package bbm.sort;

import org.junit.jupiter.api.Test;

/**
 * @author Chen Yang/CL10060-N/chen.yang@linecorp.com
 */
public class SorterTest {

    @Test
    public void testSort() {
        Sorter sorter = new MergeSorter();
        int[] result = sorter.sort(new int[] {5,2,3,1});
        sorter.print(result);
    }
}
