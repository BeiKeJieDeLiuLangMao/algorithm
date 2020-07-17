package bbm.leetcode.question;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 *
 * @author bbm
 * @date 2020/7/17
 */
public class Question35 {
    public static void main(String[] args) {
        System.out.println(new Question35().searchInsert(new int[] {3, 5, 7, 9, 10}, 8));
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right && right >= 0 && left < nums.length) {
            int mid = (left + right) / 2;
            int midValue = nums[mid];
            if (midValue < target) {
                left = mid + 1;
            } else if (midValue > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return nums[left] >= target ? left : left + 1;
    }
}
