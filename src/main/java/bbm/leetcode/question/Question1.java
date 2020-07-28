package bbm.leetcode.question;

import java.util.HashMap;
import java.util.Map;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question1 {
    public static void main(String[] args) {
        print(new Question1().twoSum(new int[] {2, 7, 11, 15}, 9));
    }

    /**
     * 对于每一个遍历到的值，我们都计算 target - i 的剩余值 complement，然后将 complement 存入 hash 表，后续遍历每个数 i 时都检查 hash
     * 表中是否存在和 i 相同的 key 有的话，说明匹配到了目标值
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
