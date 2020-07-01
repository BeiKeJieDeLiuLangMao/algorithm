package bbm.leetcode;

/**
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 *
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 *
 * 如果不存在这样的子数组，请返回 0 。
 *
 * 提示 1：
 *
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * 示例 2：
 *
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * 示例 3：
 *
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 * 示例 4：
 *
 * 输入：nums = [1,1,0,0,1,1,1,0,1]
 * 输出：4
 * 示例 5：
 *
 * 输入：nums = [0,0,0]
 * 输出：0
 *  
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-subarray-of-1s-after-deleting-one-element
 *
 * @author bbm
 * @date 2020/6/30
 */
public class Question1493 {

    public static void main(String[] args) {
        System.out.println(new Question1493().longestSubarray(new int[] {1, 1, 0, 1}));
    }

    public int longestSubarray(int[] nums) {
        int max = 0;
        int[] counts = new int[nums.length + 2];
        int index = 1;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                max = getMax(max, counts, index++, count);
                count = 0;
            }
        }
        max = getMax(max, counts, index, count);
        if (index == 1) {
            max--;
        }
        return max;
    }

    private int getMax(int max, int[] counts, int index, int count) {
        counts[index] = count;
        int temp = counts[index - 1] + counts[index];
        if (temp > max) {
            max = temp;
        }
        return max;
    }
}
