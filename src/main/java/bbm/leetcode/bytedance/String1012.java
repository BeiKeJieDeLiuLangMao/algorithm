package bbm.leetcode.bytedance;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author bbm
 * @date 2020/7/9
 */
public class String1012 {
    public static void main(String[] args) {
        System.out.println(new String1012().lengthOfLongestSubstring("d vdf"));
    }

    public int lengthOfLongestSubstring(String s) {
        int[] existedCharMap = new int[256];
        Arrays.fill(existedCharMap, -1);
        char[] queue = new char[s.length()];
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;
        for (int begin = 0, end = begin; begin < s.length(); ) {
            boolean exist = false;
            for (; end < s.length(); end++) {
                char endChar = s.charAt(end);
                if (existedCharMap[endChar] != -1) {
                    int beginIndexDiff = 0;
                    while (endIndex - startIndex > 0) {
                        char topChar = queue[startIndex++];
                        existedCharMap[topChar] = -1;
                        beginIndexDiff++;
                        if (topChar == endChar) {
                            break;
                        }
                    }
                    begin = begin + beginIndexDiff;
                    exist = true;
                    break;
                } else {
                    existedCharMap[endChar] = end;
                    queue[endIndex++] = endChar;
                    max = Math.max(max, end - begin + 1);
                }
            }
            if (!exist) {
                return max;
            }
        }
        return max;
    }
}
