package bbm.leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question20 {
    public static void main(String[] args) {
        System.out.println(new Question20().isValid("([)]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (isPushChar(s.charAt(i))) {
                stack.push(s.charAt(i));
                if (stack.size() > s.length() / 2) {
                    return false;
                }
            } else if (isPopChar(s.charAt(i))) {
                if (stack.size() == 0) {
                    return false;
                }
                char pushedChar = stack.pop();
                if (!match(pushedChar, s.charAt(i))) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    private boolean match(char pushedChar, char popChar) {
        if (popChar == ')') {
            return pushedChar == '(';
        }
        if (popChar == '}') {
            return pushedChar == '{';
        }
        if (popChar == ']') {
            return pushedChar == '[';
        }
        throw new RuntimeException("Not happen!");
    }

    public boolean isPushChar(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    public boolean isPopChar(char c) {
        return c == ')' || c == '}' || c == ']';
    }
}
