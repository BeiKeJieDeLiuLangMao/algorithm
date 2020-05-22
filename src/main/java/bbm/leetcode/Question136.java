package bbm.leetcode;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 *
 * @author bbm
 * @date 2020/5/22
 */
public class Question136 {
    public int singleNumber(int[] nums) {
        int m = 0;
        for (int i = 0; i < nums.length; i++) {
            m = m ^ nums[i];
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(new Question136().singleNumber(new int[] {4,1,2,1,2}));
    }
}
