package bbm.leetcode.question;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aaabb", k = 3
 * <p>
 * 输出:
 * 3
 * <p>
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2:
 * <p>
 * 输入:
 * s = "ababbc", k = 2
 * <p>
 * 输出:
 * 5
 * <p>
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question395 {
    public static void main(String[] args) {
        System.out.println(new Question395().longestSubstring2("aabcabb", 3));
    }

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k == 1) {
            return s.length();
        }
        TreeMap<Integer, Integer>[] map = new TreeMap[26];
        boolean[] matchedSet = new boolean[26];
        int matchedSetSize = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            TreeMap<Integer, Integer> table = map[c - 'a'];
            if (table == null) {
                table = new TreeMap<>();
                map[c - 'a'] = table;
            }
            Map.Entry<Integer, Integer> ceilEntry = table.floorEntry(i);
            if (ceilEntry == null) {
                table.put(i, 1);
            } else {
                table.put(i, ceilEntry.getValue() + 1);
                if (ceilEntry.getValue() + 1 == k) {
                    matchedSet[c - 'a'] = true;
                    matchedSetSize++;
                }
            }
        }
        if (matchedSetSize == 0) {
            return 0;
        }
        int check = k;
        int max = 0;
        while (check <= s.length()) {
            for (int i = 0; i <= s.length() - check; i++) {
                if (matchedSet[s.charAt(i) - 'a']) {
                    Set<Character> checked = new HashSet<>();
                    boolean match = true;
                    for (int j = check - 1; j >= 0; j--) {
                        char c = s.charAt(i + j);
                        if (!matchedSet[s.charAt(i) - 'a']) {
                            match = false;
                            break;
                        }
                        if (!checked.contains(c)) {
                            int countEnd = map[c - 'a'].get(i + j);
                            int countStart = map[c - 'a'].ceilingEntry(i).getValue();
                            if (countEnd - countStart >= k - 1) {
                                checked.add(c);
                            } else {
                                match = false;
                                break;
                            }
                        }
                    }
                    if (match) {
                        max = check;
                    }
                    if (max == check) {
                        break;
                    }
                }
            }
            check++;
        }
        return max;
    }

    public int longestSubstring2(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k == 1) {
            return s.length();
        }
        return getLongest(s.toCharArray(), k, 0, s.length());
    }

    private int getLongest(char[] chars, int k, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        int[] charNums = new int[26];
        int validCount = 0;
        for (int i = start; i < end; i++) {
            int index = chars[i] - 'a';
            charNums[index]++;
            if (charNums[index] == k) {
                validCount++;
            }
        }
        if (validCount == 0) {
            return 0;
        }
        while (start < end && charNums[chars[start] - 'a'] < k) {
            start++;
        }
        while (start < end && charNums[chars[end - 1] - 'a'] < k) {
            end--;
        }
        for (int i = start; i < end; i++) {
            int index = chars[i] - 'a';
            if (charNums[index] < k) {
                return Math.max(getLongest(chars, k, start, i), getLongest(chars, k, i + 1, end));
            }
        }
        return end - start;
    }
}
