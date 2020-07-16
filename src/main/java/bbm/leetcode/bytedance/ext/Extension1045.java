package bbm.leetcode.bytedance.ext;

/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * @author bbm
 * @date 2020/7/16
 */
public class Extension1045 {
    public static void main(String[] args) {
        System.out.println(new Extension1045().mySqrt(2));
    }

    /**
     * 思想是 base 从 x 出发，计算 multi = base * base 的值，如果发现 multi 大于 x，就几率一下 base 的最大值 max，然后就对 base 向小二分，
     * 如果发现 multi 小于 x 则 记录一下最小值 min，然后 base 向大二分，当 max 比 min 大 1 时，min 就是最终结果
     */
    public int mySqrt(int x) {
        long base = x;
        long min = 0;
        long max = Integer.MAX_VALUE;
        while (true) {
            long multi = base * base;
            long newBase;
            if (multi > x) {
                max = Math.min(max, base);
                newBase = (base + min) / 2;
            } else if (multi == x) {
                return (int) base;
            } else {
                min = Math.max(base, min);
                newBase = (base + max) / 2;
            }
            if (min == max - 1) {
                return (int) min;
            }
            base = newBase;
        }
    }

}
