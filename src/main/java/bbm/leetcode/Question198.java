package bbm.leetcode;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = nums[1];
        int result = Math.max(max[0], max[1]);
        for (int i = 2; i < nums.length; i++) {
            // 跳格情况 diff = 3
            if (i >= 3) {
                max[i] = Math.max(max[i], max[i-3] + nums[i]);
            }
            // 不跳格情况 diff = 2
            max[i] = Math.max(max[i], max[i-2] + nums[i]);
            result = Math.max(result, max[i]);
            // 跳超过 3 格的情况，必然比跳小于 3 格要少，因为三格中必然能多一格的值
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Question198().rob(new int[] {6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3}));
    }
}
