package bbm.leetcode;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * <p>
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 *
 * @author bbm
 * @date 2020/5/22
 */
public class Question172 {
    public static void main(String[] args) {
        System.out.println(new Question172().trailingZeroes(20));
    }

    public int trailingZeroes(int n) {
        // 当小于5时尾数没有0
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
