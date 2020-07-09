package bbm.leetcode.question;

import java.util.ArrayList;
import java.util.List;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 *
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-ranges
 *
 * @author bbm
 * @date 2020/6/5
 */
public class Question163 {
    public static void main(String[] args) {
        print(new Question163().findMissingRanges(new int[] {-2147483648, 2147483647}, -2147483648, 2147483647));
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            add2List(lower, upper, result);
            return result;
        }
        if (nums[0] > lower) {
            add2List(lower, nums[0] - 1, result);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            long diff = (long) nums[i + 1] - nums[i];
            if (diff > 1) {
                add2List(nums[i] + 1, nums[i + 1] - 1, result);
            }
        }
        if (nums[nums.length - 1] < upper) {
            add2List(nums[nums.length - 1] + 1, upper, result);
        }
        return result;
    }

    private void add2List(int min, int max, List<String> result) {
        if (min == max) {
            result.add(String.valueOf(min));
        } else {
            result.add(min + "->" + max);
        }
    }
}
