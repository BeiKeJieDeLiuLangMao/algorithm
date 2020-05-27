package bbm.leetcode;

import java.util.Arrays;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        int[] duplicate = new int[128];
        for (int i = 0; i < s.length() - maxLength; ) {
            Arrays.fill(duplicate, -1);
            boolean findDuplicate = false;
            for (int j = i; j < s.length(); j++) {
                if (duplicate[s.charAt(j)] != -1) {
                    int newLength = j - i;
                    if (newLength > maxLength) {
                        maxLength = newLength;
                    }
                    i = duplicate[s.charAt(j)] + 1;
                    duplicate[s.charAt(j)] = j;
                    findDuplicate = true;
                    break;
                } else {
                    duplicate[s.charAt(j)] = j;
                }
            }
            if (!findDuplicate) {
                int newLength = s.length() - i;
                if (newLength > maxLength) {
                    maxLength = newLength;
                }
                break;
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        int[] duplicate = new int[128];
        Arrays.fill(duplicate, -1);
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            begin = Math.max(begin, duplicate[index] + 1);
            maxLength = Math.max(maxLength, i - begin + 1);
            duplicate[index] = i;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Question3().lengthOfLongestSubstring("abcabcbb"));
    }
}
