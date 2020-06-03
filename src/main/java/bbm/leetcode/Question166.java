package bbm.leetcode;

import java.util.Arrays;

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

    private static int MAX_LOOP_COUNT = 32;
    private int loopPartSize = 1;

    public static void main(String[] args) {
        System.out.println(new Question166().fractionToDecimal(-2147483648, -1994));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        loopPartSize = 1;
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
            while (left != 0) {
                left *= 10;
                num = left / denominator;
                if (num != 0) {
                    rightPartNumber.append(Math.abs(num));
                    left = left % denominator;
                } else {
                    rightPartNumber.append(0);
                }
                StringBuilder handledStringBuilder = checkNotLoopToMuch(rightPartNumber);
                if (handledStringBuilder != rightPartNumber) {
                    return resultBuilder.append(handledStringBuilder).toString();
                }
            }
            resultBuilder.append(rightPartNumber);
        }
        return resultBuilder.toString();
    }

    private StringBuilder checkNotLoopToMuch(StringBuilder number) {
        char[] checkData = number.toString().toCharArray();
        if (checkData.length < MAX_LOOP_COUNT) {
            return number;
        }
        int loopPartSize = 1;
        int checkEnd = checkData.length - loopPartSize;
        for (int i = 0; i < MAX_LOOP_COUNT; i++) {
            boolean loop = false;
            while (loopPartSize < checkData.length / 2) {
                if (checkEquals(checkData, checkEnd - loopPartSize, checkData.length - loopPartSize, checkData.length - 1)) {
                    loop = true;
                    break;
                } else {
                    loopPartSize++;
                    checkEnd = checkData.length - loopPartSize;
                    i = 0;
                }
            }
            if (!loop) {
                return number;
            } else {
                checkEnd -= loopPartSize;
            }
        }
        while (checkEnd >= 0) {
            if (checkEquals(checkData, checkEnd - loopPartSize, checkData.length - loopPartSize, checkData.length - 1)) {
                checkEnd -= loopPartSize;
            } else {
                break;
            }
        }
        return new StringBuilder()
            .append(String.valueOf(Arrays.copyOf(checkData, checkEnd)))
            .append("(")
            .append(String.valueOf(Arrays.copyOfRange(checkData, checkData.length - loopPartSize, checkData.length)))
            .append(')');
    }

    private boolean checkEquals(char[] data, int start, int matcherStart, int matcherEnd) {
        if (start < 0) {
            return false;
        }
        for (int i = 0; i <= matcherEnd - matcherStart; i++) {
            if (data[i + start] != data[matcherStart + i]) {
                return false;
            }
        }
        return true;
    }
}
