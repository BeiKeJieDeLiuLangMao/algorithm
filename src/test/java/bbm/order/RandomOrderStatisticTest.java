package bbm.order;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
class RandomOrderStatisticTest {
    @Test
    public void testGetNSmallestNumber() {
        doTest(new RandomOrderStatistic());
        doTest(new StableOrderStatistic());
    }

    private void doTest(OrderStatistic orderStatistic) {
        int dataSize = 10000;
        int[] data = new int[dataSize];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < dataSize; i++) {
            data[i] = rand.nextInt(dataSize);
        }
        Arrays.sort(data);
        Collections.reverse(Ints.asList(data));
        int rightResult = Ints.asList(data).stream().distinct().collect(Collectors.toList()).get(2);
        int result = orderStatistic.getNLargestNumber(data);
        Assertions.assertEquals(rightResult, result);
    }
}