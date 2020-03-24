package bbm.order;

import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
class RandomOrderStatisticTest {
    @Test
    public void testGetNSmallestNumber() {
        StableOrderStatistic selector = new StableOrderStatistic();
        int result = selector.getNLargestNumber(new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6});
        System.out.println(result);
    }
}