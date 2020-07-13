package bbm.leetcode.bytedance.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bbm.leetcode.common.Utils.print;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1020 {
    public static void main(String[] args) {
        print(new Array1020().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 先对数据进行排序，然后利用 mid 数和 right 数的指针逼近的方法，来找到所有满足情况的组合
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = nums[i];
            if (left > 0) {
                break;
            }
            int mid = i + 1;
            int right = nums.length - 1;
            while (mid < right) {
                int sum = left + nums[mid] + nums[right];
                if (sum < 0) {
                    while (mid + 1 < nums.length && nums[mid] == nums[++mid])
                        ;
                } else if (sum > 0) {
                    while (right - 1 > 0 && nums[right] == nums[--right])
                        ;
                } else {
                    result.add(Arrays.asList(left, nums[mid], nums[right]));
                    while (mid + 1 < nums.length && nums[mid] == nums[++mid])
                        ;
                }
            }
            while (i + 1 < nums.length - 2 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return result;
    }
}
