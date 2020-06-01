package bbm.leetcode;

import java.util.LinkedList;
import java.util.List;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2:abc
 * 3:def
 * 4:ghi
 * 5:jkl
 * 6:mno
 * 7:pqrs
 * 8:tuv
 * 9:wxyz
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question17 {

    private static char[][] MAP = new char[][] {
        new char[] {'a', 'b', 'c'},
        new char[] {'d', 'e', 'f'},
        new char[] {'g', 'h', 'i'},
        new char[] {'j', 'k', 'l'},
        new char[] {'m', 'n', 'o'},
        new char[] {'p', 'q', 'r', 's'},
        new char[] {'t', 'u', 'v'},
        new char[] {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int[] indexes = new int[digits.length()];
        while (true) {
            for (int i = 0; i < digits.length(); i++) {
                int index = digits.charAt(i) - '2';
                stringBuilder.append(MAP[index][indexes[i]]);
                if (i == digits.length() - 1) {
                    result.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    indexes[i]++;
                }
                int current = i;
                int currentCharIndex = index;
                while (current >= 0) {
                    if (indexes[current] == MAP[currentCharIndex].length) {
                        indexes[current] = 0;
                        current--;
                        if (current >= 0) {
                            indexes[current]++;
                            currentCharIndex = digits.charAt(current) - '2';
                        }
                    } else {
                        break;
                    }
                }
                if (current == -1) {
                    return result;
                }
            }
        }
    }

    public static void main(String[] args) {
        print(new Question17().letterCombinations("23"));
    }
}
