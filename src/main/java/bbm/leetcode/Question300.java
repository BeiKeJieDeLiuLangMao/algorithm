package bbm.leetcode;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 *
 * @author bbm
 * @date 2020/5/27
 */
public class Question300 {
    public static void main(String[] args) {
        System.out.println(new Question300().lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 4}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            boolean set = false;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                    set = true;
                }
            }
            if (!set) {
                dp[i] = 1;
            }
        }
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sorted = new int[nums.length];
        int index = 0;
        sorted[index] = nums[0];
        for (int num : nums) {
            if (num > sorted[index]) {
                sorted[++index] = num;
            } else {
                int left = 0, right = index;
                int less = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (num <= sorted[mid]) {
                        right = mid - 1;
                    } else {
                        less = mid;
                        left = mid + 1;
                    }
                }
                if (sorted[less] >= num) {
                    less--;
                }
                sorted[less + 1] = num;
            }
        }
        return index + 1;
    }
}
