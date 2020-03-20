package bbm.sequence;

import org.junit.jupiter.api.Test;

/**
 * @author Chen Yang/CL10060-N/chen.yang@linecorp.com
 */
public class MaxSubSequenceTest {
    @Test
    public void testMaxSubSeq() {
        MaxSubSequence maxSubSequence = new MaxSubSequence();
        int result = maxSubSequence.maxSubArray(new int[] {-2, -1});
        System.out.println(result);
    }
}
