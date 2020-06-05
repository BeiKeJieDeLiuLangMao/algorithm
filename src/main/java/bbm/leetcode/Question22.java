package bbm.leetcode;

import java.util.LinkedList;
import java.util.List;

import static bbm.leetcode.common.Utils.print;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question22 {
    public static void main(String[] args) {
        print(new Question22().generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        if (n == 1) {
            result.add("()");
        } else if (n > 1) {
            dfs(result, n, n, "");
        }
        return result;
    }

    public void dfs(List<String> result, int left, int right, String path) {
        if (left == right) {
            if (left == 0) {
                result.add(path);
                return;
            }
            dfs(result, left - 1, right, path + "(");
        } else if (left < right) {
            if (left > 0) {
                dfs(result, left - 1, right, path + "(");
            }
            dfs(result, left, right - 1, path + ")");
        } else {
            throw new RuntimeException("Should not happen!");
        }
    }
}
