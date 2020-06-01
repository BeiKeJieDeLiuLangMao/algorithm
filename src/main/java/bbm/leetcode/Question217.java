package bbm.leetcode;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 *
 * @author bbm
 * @date 2020/5/22
 */
public class Question217 {
    public static void main(String[] args) {
        System.out.println(new Question217().containsDuplicate(new int[] {1, 2, 3, 4}));
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        boolean needPlus = false;
        if (min < 0) {
            needPlus = true;
            min = Math.abs(max);
            max = max + min;
        }
        boolean[] table = new boolean[max + 1];
        if (needPlus) {
            for (int i : nums) {
                int temp = min + i;
                if (table[temp]) {
                    return true;
                } else {
                    table[temp] = true;
                }
            }
        } else {
            for (int i : nums) {
                if (table[i]) {
                    return true;
                } else {
                    table[i] = true;
                }
            }
        }
        return false;
    }
}
