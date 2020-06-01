package bbm.leetcode;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 *
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 *
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question227 {
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*' || chars[i] == '/') {
                int preNumIndexEnd = getPreNumEnd(chars, i - 1);
                int preNumIndexStart = getPreNumStart(chars, preNumIndexEnd);
                long preNum = getNum(chars, preNumIndexStart, preNumIndexEnd);
                int afterNumIndexStart = getAfterNumStart(chars, i + 1);
                int afterNumIndexEnd = getAfterNumEnd(chars, afterNumIndexStart);
                long afterNum = getNum(chars, afterNumIndexStart, afterNumIndexEnd);
                long result;
                if (chars[i] == '*') {
                    result = preNum * afterNum;
                } else {
                    result = preNum / afterNum;
                }
                fixCharsByResult(chars, preNumIndexStart, afterNumIndexEnd, result);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '+' || chars[i] == '-') {
                int preNumIndexEnd = getPreNumEnd(chars, i - 1);
                int preNumIndexStart = getPreNumStart(chars, preNumIndexEnd);
                long preNum = getNum(chars, preNumIndexStart, preNumIndexEnd);
                int afterNumIndexStart = getAfterNumStart(chars, i + 1);
                int afterNumIndexEnd = getAfterNumEnd(chars, afterNumIndexStart);
                long afterNum = getNum(chars, afterNumIndexStart, afterNumIndexEnd);
                long result;
                if (chars[i] == '+') {
                    result = preNum + afterNum;
                } else {
                    result = preNum - afterNum;
                }
                fixCharsByResult(chars, preNumIndexStart, afterNumIndexEnd, result);
            }
        }
        int result = 0;
        boolean neg = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (chars[i] == '?') {
                    neg = true;
                } else {
                    result = result * 10 + (chars[i] - '0');
                }
            }
        }
        if (neg) {
            result *= -1;
        }
        return result;
    }

    private long getNum(char[] chars, int start, int end) {
        long result = 0;
        boolean neg = false;
        for (int i = start; i <= end; i++) {
            if (chars[i] == '?') {
                neg = true;
            } else {
                result = result * 10 + (chars[i] - '0');
            }
        }
        if (neg) {
            result *= -1;
        }
        return result;
    }

    private void fixCharsByResult(char[] chars, int start, int end, long result) {
        boolean neg = result < 0;
        if (result < 0) {
            result *= -1;
        }
        for (int j = end; j >= start; j--) {
            if (result != 0 || j == end) {
                chars[j] = (char) ('0' + (result % 10));
                result /= 10;
            } else if (neg) {
                chars[j] = '?';
                neg = false;
            } else {
                chars[j] = ' ';
            }
        }
    }

    private int getAfterNumEnd(char[] chars, int i) {
        while (i + 1 < chars.length && isNum(chars[i])) {
            i++;
        }
        return isNum(chars[i]) ? i : i - 1;
    }

    private boolean isNum(char c) {
        return (c - '0' >= 0 && c - '0' <= 9) || c == '?';
    }

    private int getAfterNumStart(char[] chars, int i) {
        while (chars[i] == ' ') {
            i++;
        }
        return i;
    }

    private int getPreNumStart(char[] chars, int i) {
        while (i >= 1 && isNum(chars[i])) {
            i--;
        }
        return isNum(chars[i]) ? i : i + 1;
    }

    private int getPreNumEnd(char[] chars, int i) {
        while (chars[i] == ' ') {
            i--;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new Question227().calculate("0-2147483647"));
    }
}
