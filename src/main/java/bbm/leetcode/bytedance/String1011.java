package bbm.leetcode.bytedance;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * @author bbm
 * @date 2020/7/9
 */
public class String1011 {
    public static void main(String[] args) {
        System.out.print(new String1011().reverseWords("a"));
    }

    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        boolean findStart = false;
        int end = -1;
        boolean addFirst = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (findStart) {
                if (s.charAt(i) == ' ') {
                    if (!addFirst) {
                        addFirst = true;
                    } else {
                        builder.append(' ');
                    }
                    builder.append(s, i + 1, end);
                    end = -1;
                    findStart = false;
                }
            } else {
                if (s.charAt(i) != ' ') {
                    end = i + 1;
                    findStart = true;
                }
            }
        }
        if (end != -1) {
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(s, 0, end);
        }
        return builder.toString();
    }
}
