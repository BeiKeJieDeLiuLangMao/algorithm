package bbm.leetcode.question;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 示例 1:
 *
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 *
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 *
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question166 {

    public static void main(String[] args) {
        System.out.println(new Question166().fractionToDecimal(1, 6));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long num = (long) numerator / denominator;
        long left = numerator % denominator;
        StringBuilder resultBuilder = new StringBuilder();
        int sign = 1;
        if (numerator > 0 && denominator < 0) {
            sign = -1;
        } else if (numerator < 0 && denominator > 0) {
            sign = -1;
        }
        if (num == 0 && sign == -1) {
            resultBuilder.append('-');
        }
        resultBuilder.append(num);
        if (left != 0) {
            resultBuilder.append('.');
            StringBuilder rightPartNumber = new StringBuilder();
            Map<Long, Integer> leftSet = new HashMap<>();
            leftSet.put(left, 0);
            boolean findLoop = false;
            while (left != 0) {
                left *= 10;
                num = left / denominator;
                if (num != 0) {
                    rightPartNumber.append(Math.abs(num));
                    left = left % denominator;
                } else {
                    rightPartNumber.append(0);
                }
                if (leftSet.containsKey(left)) {
                    findLoop = true;
                    break;
                } else {
                    leftSet.put(left, rightPartNumber.length());
                }
            }
            if (!findLoop) {
                resultBuilder.append(rightPartNumber);
            } else {
                String rightPart = rightPartNumber.toString();
                int loopStart = leftSet.get(left);
                resultBuilder.append(rightPart, 0, loopStart);
                if (loopStart < rightPart.length()) {
                    resultBuilder.append('(').append(rightPart.substring(loopStart)).append(')');
                }
            }
        }
        return resultBuilder.toString();
    }

}
