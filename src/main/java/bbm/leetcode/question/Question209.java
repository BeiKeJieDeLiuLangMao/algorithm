package bbm.leetcode.question;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 *  
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *  
 *
 * 进阶：
 *
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 *
 * @author bbm
 * @date 2020/7/1
 */
public class Question209 {
    public static void main(String[] args) {
        System.out.println(new Question209().minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int min = nums.length + 1;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= s) {
                min = Math.min(min, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return min == nums.length + 1 ? 0 : min;
    }
}
