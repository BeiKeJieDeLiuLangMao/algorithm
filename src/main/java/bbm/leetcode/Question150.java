package bbm.leetcode;

/**
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question150 {
    public static void main(String[] args) {
        System.out.println(new Question150().evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int stackIndex = 0;
        for (String str : tokens) {
            if (isOperator(str)) {
                int num2 = stack[--stackIndex];
                int num1 = stack[--stackIndex];
                int result = compute(num1, num2, str);
                stack[stackIndex++] = result;
            } else {
                stack[stackIndex++] = Integer.parseInt(str);
            }
        }
        return stack[0];
    }

    private int compute(int num1, int num2, String str) {
        switch (str) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        throw new RuntimeException("Not implement");
    }

    private boolean isOperator(String c) {
        return "+".equals(c) || "-".equals(c) || "*".equals(c) || "/".equals(c);
    }
}
