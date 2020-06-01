package bbm.leetcode;

import java.util.Arrays;
import java.util.Random;

import static bbm.leetcode.Utils.print;

/**
 * 打乱一个没有重复元素的数组。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * <p>
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * <p>
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question384 {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{-9, 10, 100, 20});
        print(solution.reset());
        print(solution.shuffle());
        print(solution.shuffle());
        print(solution.shuffle());
        print(solution.shuffle());
        print(solution.shuffle());
    }

    static class Solution {

        Random random = new Random(System.currentTimeMillis());
        private final int[] original;

        public Solution(int[] nums) {
            this.original = nums;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return original;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            int[] result = Arrays.copyOf(original, original.length);
            for (int i = 0; i < result.length; i++) {
                int exchangeIndex = i + random.nextInt(result.length - i);
                int temp = result[exchangeIndex];
                result[exchangeIndex] = result[i];
                result[i] = temp;
            }
            return result;
        }
    }
}
