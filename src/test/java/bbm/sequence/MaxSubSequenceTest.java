package bbm.sequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
public class MaxSubSequenceTest {
    @Test
    public void testMaxSubSeq() {
        MaxSubSequence maxSubSequence = new MaxSubSequence();
        int result = maxSubSequence.maxSubArray2(new int[] {-2, -1, 1, 1, 4, -3, -4, 3});
        Assertions.assertEquals(6, result);
    }
}
