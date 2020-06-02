package bbm.leetcode;

import java.util.Arrays;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 示例 1:
 *
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * 示例 2:
 *
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 *
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wiggle-sort-ii
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question324 {
    public static void main(String[] args) {
        int[] data = new int[] {1};
        new Question324().wiggleSort2(data);
        print(data);
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] left = Arrays.copyOfRange(nums, 0, (int) Math.ceil((double) nums.length / 2));
        int[] right = Arrays.copyOfRange(nums, (int) Math.ceil((double) nums.length / 2), nums.length);
        reverse(left);
        reverse(right);
        int index = 0;
        for (int i = 0; i < left.length; i++) {
            nums[index++] = left[i];
            if (i < right.length) {
                nums[index++] = right[i];
            }
        }
    }

    private void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            switchItem(nums, left, right);
            left++;
            right--;
        }
    }

    public void wiggleSort2(int[] nums) {
        int mid = (int) Math.ceil((double) nums.length / 2);
        quickSort(nums, mid, 0, nums.length - 1);
        int[] left = Arrays.copyOfRange(nums, 0, (int) Math.ceil((double) nums.length / 2));
        int[] right = Arrays.copyOfRange(nums, (int) Math.ceil((double) nums.length / 2), nums.length);
        reverse(left);
        reverse(right);
        int index = 0;
        for (int i = 0; i < left.length; i++) {
            nums[index++] = left[i];
            if (i < right.length) {
                nums[index++] = right[i];
            }
        }
    }

    private void quickSort(int[] nums, int target, int start, int end) {
        if (target > end) {
            return;
        }
        int pivotNum = nums[end];
        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivotNum) {
                switchItem(nums, i, left + 1);
                left++;
            }
        }
        int pivotRightIndex = left + 1;
        switchItem(nums, pivotRightIndex, end);
        if (pivotRightIndex != target) {
            if (pivotRightIndex > target) {
                quickSort(nums, target, start, pivotRightIndex - 1);
            } else {
                quickSort(nums, target, pivotRightIndex + 1, end);
            }
        }
    }

    private void switchItem(int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }
}
