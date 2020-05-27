package bbm.leetcode;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 27
 * 输出: true
 * 示例 2:
 *
 * 输入: 0
 * 输出: false
 * 示例 3:
 *
 * 输入: 9
 * 输出: true
 * 示例 4:
 *
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-three
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question326 {
    public boolean isPowerOfThree(int n) {
        // Math.pow(3, 19) 是小于 Integer.MAX_VALUE 的最大的3的幂
        return n > 0 && 1.162261467E9 % n == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Question326().isPowerOfThree(33));
    }
}
