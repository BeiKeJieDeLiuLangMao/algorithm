package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question34 {
    public int[] searchRange(int[] nums, int target) {
        int start = binarySearch(nums, 0, nums.length - 1, target, true);
        if (start == -1) {
            return new int[] {-1, -1};
        } else {
            int end = binarySearch(nums, 0, nums.length - 1, target, false);
            return new int[] {start, end};
        }
    }

    private int binarySearch(int[] nums, int start, int end, int target, boolean findStart) {
        if (start > end) {
            return -1;
        } else if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return -1;
            }
        }
        int targetIndex = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                targetIndex = mid;
                if (findStart) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (midValue > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return targetIndex;
    }

    public static void main(String[] args) {
        print(new Question34().searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6));
    }
}
