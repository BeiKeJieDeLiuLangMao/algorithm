package bbm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question169 {
    public static void main(String[] args) {
        System.out.println(new Question169().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            Integer previous = numCountMap.putIfAbsent(num, 1);
            if (previous != null) {
                int count = numCountMap.get(num) + 1;
                if (count > nums.length / 2) {
                    return num;
                }
                numCountMap.put(num, count);
            }
        }
        throw new RuntimeException("Should not happen");
    }

    public int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
