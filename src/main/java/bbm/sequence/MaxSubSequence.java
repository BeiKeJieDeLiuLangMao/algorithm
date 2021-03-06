package bbm.sequence;

/**
 * 基于分治思想的最大子序列，递归地将序列分割成两半，然后比较左半段，右半段，和中间跨越的段，最终取最大的作为结果
 *
 * 时间复杂度: O(n*log(n))
 * 空间复杂度: O(log(n)) 栈
 *
 * @author bbm
 */
public class MaxSubSequence {
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return findMaxArray(nums, 0, nums.length - 1);
    }

    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            int sum = dp[i - 1] + nums[i];
            if (sum > nums[i]) {
                dp[i] = sum;
            } else {
                dp[i] = nums[i];
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    private int findMaxArray(int[] nums, int low, int max) {
        if (low == max) {
            return nums[low];
        }
        int mid = (low + max) / 2;
        int leftMax = findMaxArray(nums, low, mid);
        int rightMax = findMaxArray(nums, mid + 1, max);
        int midMax = findCrossArray(nums, low, mid, max);
        return Math.max(Math.max(leftMax, rightMax), midMax);
    }

    private int findCrossArray(int[] nums, int low, int mid, int max) {
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        for (int x = mid; x >= low; x--) {
            sum += nums[x];
            if (sum > leftMax) {
                leftMax = sum;
            }
        }
        sum = 0;
        int rightMax = Integer.MIN_VALUE;
        for (int x = mid + 1; x <= max; x++) {
            sum += nums[x];
            if (sum > rightMax) {
                rightMax = sum;
            }
        }
        return leftMax + rightMax;
    }
}
