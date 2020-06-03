package bbm.leetcode;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 * 提示：
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question29 {
    public static void main(String[] args) {
        System.out.println(new Question29().divide(-2147483648, 2));
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == 0) {
            return 0;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        int sign = 1;
        // 必须用负数描述，否则会溢出
        if (dividend > 0 && divisor < 0) {
            sign = -1;
            dividend *= -1;
        } else if (dividend < 0 && divisor > 0) {
            sign = -1;
            divisor *= -1;
        } else if (dividend > 0 && divisor > 0) {
            dividend *= -1;
            divisor *= -1;
        }
        return sign * div(dividend, divisor);
    }

    private int div(int dividend, int divisor) {
        if (dividend > divisor) {
            return 0;
        }
        int count = 1;
        int currentValue = divisor;
        while (currentValue + currentValue < 0 && currentValue + currentValue > dividend) {
            count += count;
            currentValue += currentValue;
        }
        return count + div(dividend - currentValue, divisor);
    }
}
