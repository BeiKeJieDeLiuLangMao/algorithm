package bbm.leetcode.question;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 *
 * @author bbm
 * @date 2020/5/27
 */
public class Question5 {

    public static void main(String[] args) {
        System.out.println(new Question5().longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        AtomicInteger max = new AtomicInteger(1);
        AtomicReference<String> maxString = new AtomicReference<>("" + s.charAt(0));
        IntStream.range(0, s.length()).forEach(singleIndex -> {
            if (singleIndex != 0 && singleIndex != s.length() - 1) {
                int left = singleIndex - 1, right = singleIndex + 1;
                computeLongest(s, max, maxString, left, right);
            }
        });
        IntStream.range(0, s.length()).forEach(doubleIndex -> {
            if (doubleIndex + 1 < s.length() && s.charAt(doubleIndex) == s.charAt(doubleIndex + 1)) {
                int left = doubleIndex - 1, right = doubleIndex + 2;
                computeLongest(s, max, maxString, left, right);
            }
        });
        return maxString.get();
    }

    private void computeLongest(String s, AtomicInteger max, AtomicReference<String> maxString, int left, int right) {
        for (; left >= 0 && right < s.length(); left--, right++) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
        }
        left++;
        int length = right - left;
        if (length > max.get()) {
            max.set(length);
            maxString.set(s.substring(left, right));
        }
    }
}
