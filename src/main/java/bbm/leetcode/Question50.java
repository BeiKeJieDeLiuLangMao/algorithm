package bbm.leetcode;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question50 {
    public static void main(String[] args) {
        System.out.println(new Question50().myPow(1,
            Integer.MIN_VALUE));
    }

    public double myPow(double x, int n) {
        boolean nLarger0 = n > 0;
        long longN = n;
        longN = Math.abs(longN);
        double result = 1;
        long finalPow = 0;
        while (finalPow != longN) {
            long current = 1;
            double temp = x;
            while (current * 2 <= longN - finalPow) {
                temp *= temp;
                current *= 2;
            }
            result *= temp;
            finalPow += current;
        }
        if (!nLarger0) {
            result = 1 / result;
        }
        return result;
    }
}
