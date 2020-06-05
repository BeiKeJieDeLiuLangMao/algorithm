package bbm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question131 {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        char[] data = s.toCharArray();
        Queue<Context> queue = new LinkedList<>();
        queue.add(new Context(0, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Context context = queue.poll();
            for (int i = 0; i < s.length() - context.startIndex; i++) {
                if (context.startIndex + i == s.length() - 1 && checkValid(data, context.startIndex, context.startIndex + i)) {
                    context.data.add(s.substring(context.startIndex, context.startIndex + i + 1));
                    result.add(context.data);
                } else if (checkValid(data, context.startIndex, context.startIndex + i)) {
                    List<String> newList = new ArrayList<>(context.data);
                    newList.add(s.substring(context.startIndex, context.startIndex + i + 1));
                    queue.add(new Context(context.startIndex + i + 1, newList));
                }
            }
        }
        return result;
    }

    private static class Context {
        private int startIndex;
        private List<String> data;

        private Context(int startIndex, List<String> data) {
            this.startIndex = startIndex;
            this.data = data;
        }
    }

    private boolean checkValid(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        print(new Question131().partition2("efe"));
    }

    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<>();
        char[] data = s.toCharArray();
        List<String> current = new ArrayList<>();
        getAll(result, current, data, 0);
        return result;
    }

    private void getAll(List<List<String>> result, List<String> current, char[] data, int startIndex) {
        if (startIndex == data.length) {
            result.add(new ArrayList<>(current));
        } else {
            for (int i = startIndex; i < data.length; i++) {
                if (checkValid(data, startIndex, i)) {
                    current.add(String.valueOf(data, startIndex, i - startIndex + 1));
                    getAll(result, current, data, i + 1);
                    current.remove(current.size() - 1);
                }
            }
        }
    }
}
