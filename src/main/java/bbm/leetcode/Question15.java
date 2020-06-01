package bbm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bbm.leetcode.Utils.print;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question15 {
    public static void main(String[] args) {
        print(new Question15().threeSum(new int[] {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left + 1 < nums.length && nums[left] == nums[++left]) {
                    }
                } else if (sum < 0) {
                    while (left + 1 < nums.length && nums[left] == nums[++left]) {
                    }
                } else {
                    while (right - 1 > 0 && nums[right] == nums[--right]) {
                    }
                }
            }
            while (i + 1 < nums.length - 2 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return result;
    }
}
