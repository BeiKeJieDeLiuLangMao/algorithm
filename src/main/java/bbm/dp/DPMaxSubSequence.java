package bbm.dp;

/**
 * 这里我们用动态规划的思想重新解最大子序列的问题
 *
 * @author bbm
 */

public class DPMaxSubSequence {
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // end 记录了以 i 为结尾的序列中最大的子序列和
        int[] end = new int[nums.length];
        end[0] = nums[0];
        int max = end[0];
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] > end[i - 1]) {
                if (end[i-1] > 0) {
                    end[i] = end[i - 1] + nums[i];
                } else {
                    end[i] = nums[i];
                }
            } else {
                end[i] = end[i - 1] + nums[i];
            }
            if (end[i] > max) {
                max = end[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        DPMaxSubSequence maxSubSequence = new DPMaxSubSequence();
        int result = maxSubSequence.maxSubArray(new int[] {-2, -1, 1 ,1, 4, -3, -4, 3});
        System.out.println(result);
    }
}

