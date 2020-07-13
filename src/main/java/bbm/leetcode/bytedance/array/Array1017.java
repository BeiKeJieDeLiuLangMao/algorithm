package bbm.leetcode.bytedance.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1017 {
    public static void main(String[] args) {
        System.out.println(new Array1017().search(new int[] {3, 1}, 3));
    }

    public int search(int[] nums, int target) {
        int leftEnd = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length) {
                if (nums[i + 1] < nums[i]) {
                    leftEnd = i;
                }
            }
        }
        int result = search(nums, 0, leftEnd, target);
        if (result >= 0) {
            return result;
        }
        result = search(nums, leftEnd + 1, nums.length - 1, target);
        return result;
    }

    public int search(int[] nums, int begin, int end, int target) {
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
