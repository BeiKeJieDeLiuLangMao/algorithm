package bbm.leetcode;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question33 {

    public static void main(String[] args) {
        System.out.println(new Question33().search(new int[] {5, 1, 2, 3, 4}, 1));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        if (target < nums[left] && target > nums[right]) {
            return -1;
        }
        while (left <= right) {
            if (target == nums[left]) {
                return left;
            }
            if (target == nums[right]) {
                return right;
            }
            int mid = (left + right) / 2;
            if (target > nums[left]) {
                if (nums[mid] > nums[left]) {
                    // 升序
                    if (target == nums[mid]) {
                        return mid;
                    } else if (target < nums[mid]) {
                        left++;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                        right--;
                    }
                } else {
                    // 乱序
                    if (target == nums[mid]) {
                        return mid;
                    } else {
                        left++;
                        right--;
                    }
                }
            } else {
                if (nums[mid] < nums[right]) {
                    // 升序
                    if (target == nums[mid]) {
                        return mid;
                    } else if (target < nums[mid]) {
                        left++;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                        right--;
                    }
                } else {
                    // 乱序
                    if (target == nums[mid]) {
                        return mid;
                    } else {
                        left++;
                        right--;
                    }
                }
            }
        }
        return -1;
    }
}
