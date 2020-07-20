package bbm.leetcode.question;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表
 * 和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 *
 * @author bbm
 * @date 2020/7/20
 */
public class Question312 {
    public static void main(String[] args) {
        System.out.println(new Question312().maxCoins(new int[] {3}));
    }

    /**
     * 本题使用了动态规划的思想，分别挪动 left mid right 指针，计算 leftVal * midVal * rightVal + left2MidMax + mid2RightMax
     */
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        int[] val = new int[nums.length + 2];
        val[0] = val[nums.length + 1] = 1;
        for (int i = 1; i <= nums.length; i++) {
            val[i] = nums[i - 1];
        }
        for (int left = nums.length - 1; left >= 0; left--) {
            for (int right = left + 2; right <= nums.length + 1; right++) {
                for (int mid = left + 1; mid < right; mid++) {
                    int sum = val[left] * val[mid] * val[right];
                    sum += dp[left][mid] + dp[mid][right];
                    dp[left][right] = Math.max(sum, dp[left][right]);
                }
            }
        }
        return dp[0][nums.length + 1];
    }
}
