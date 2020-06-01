package bbm.leetcode;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 *
 * @author bbm
 * @date 30/5/2020
 */
public class Question55 {
    public static void main(String[] args) {
        System.out.println(new Question55().canJump(new int[]{2, 3, 1, 1, 4}));
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int endIndex = nums.length - 1;
        boolean[] dp = new boolean[nums.length];
        dp[endIndex] = true;
        int minTreeIndex = endIndex;
        for (int i = nums.length - 2; i >= 0; i--) {
            int maxJump = nums[i];
            if (i + maxJump >= endIndex || dp[i + maxJump]) {
                for (int j = 0; j <= maxJump && j + i < nums.length; j++) {
                    if (j >= minTreeIndex) {
                        minTreeIndex = i;
                        break;
                    } else {
                        dp[j + i] = true;
                    }
                }
            }
        }
        return dp[0];
    }
}
