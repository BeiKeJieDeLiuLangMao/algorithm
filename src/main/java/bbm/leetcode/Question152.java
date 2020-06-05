package bbm.leetcode;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question152 {
    public static void main(String[] args) {
        System.out.println(new Question152().maxProduct(new int[] {2, 3, -2, 4}));
    }

    public int maxProduct(int[] nums) {
        int[] minMemo = new int[nums.length];
        int[] maxMemo = new int[nums.length];
        minMemo[0] = nums[0];
        maxMemo[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minMemo[i] = Math.min(minMemo[i - 1] * nums[i], Math.min(nums[i], maxMemo[i - 1] * nums[i]));
            maxMemo[i] = Math.max(minMemo[i - 1] * nums[i], Math.max(nums[i], maxMemo[i - 1] * nums[i]));
            if (maxMemo[i] > max) {
                max = maxMemo[i];
            }
        }
        return max;
    }

    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int num = 1;
        for (int value : nums) {
            num *= value;
            if (num > max) {
                max = num;
            }
            if (num == 0) {
                num = 1;
            }
        }
        num = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            num *= nums[i];
            if (num > max) {
                max = num;
            }
            if (num == 0) {
                num = 1;
            }
        }
        return max;
    }
}
