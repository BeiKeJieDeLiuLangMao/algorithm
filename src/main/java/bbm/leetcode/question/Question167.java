package bbm.leetcode.question;

import java.util.HashMap;
import java.util.Map;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 *
 * @author bbm
 * @date 2020/7/20
 */
public class Question167 {
    public static void main(String[] args) {
        print(new Question167().twoSum(new int[] {2, 7, 11, 15}, 9));
    }

    /**
     * 通过一个 Map 来保存所有能配对组成 target 的期待值，遍历数组中的每一个数，如果该数不在 Map 中，就计算 target - currentNum 得到期待值
     * 然后将期待值以及当前数的下标加入到 Map 中
     */
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> wanted = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (wanted.containsKey(num)) {
                return new int[] {wanted.get(num) + 1, i + 1};
            } else {
                int left = target - num;
                wanted.put(left, i);
            }
        }
        return new int[0];
    }

    /**
     * 双指针思路，一个指针指向头，一个指向尾，计算它们的和，如果大于 target 尾指针左移，小于 target 头指针右移，等于 target 输出
     */
    public int[] twoSum2(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[] {low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[] {-1, -1};
    }
}
