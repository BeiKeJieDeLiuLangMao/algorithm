package bbm.leetcode.bytedance.string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @author bbm
 * @date 2020/7/9
 */
public class String1014 {
    public static void main(String[] args) {
        System.out.println(new String1014().longestCommonPrefix(new String[] {
            "dog", "racecar", "car"
        }));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int index = 0;
        StringBuilder prefix = new StringBuilder();
        while (true) {
            boolean arrivedEnd = false;
            for (int i = 0; i < strs.length; i++) {
                if (index >= strs[i].length()) {
                    arrivedEnd = true;
                    break;
                }
            }
            if (arrivedEnd) {
                break;
            }
            char c = strs[0].charAt(index);
            boolean notEqual = false;
            for (int i = 1; i < strs.length; i++) {
                if (c != strs[i].charAt(index)) {
                    notEqual = true;
                    break;
                }
            }
            if (notEqual) {
                break;
            }
            prefix.append(c);
            index++;
        }
        return prefix.toString();
    }
}
