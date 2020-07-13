package bbm.leetcode.bytedance.string;

import java.util.HashMap;
import java.util.Map;

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
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author bbm
 * @date 2020/7/9
 */
public class String1016Timeout {
    public static void main(String[] args) {
        System.out.println(new String1016Timeout().checkInclusion("helo", "ooolleoooleh"));
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> set = new HashMap<>();
        for (char c : s1.toCharArray()) {
            Integer previous = set.putIfAbsent(c, 1);
            if (previous != null) {
                set.put(c, previous + 1);
            }
        }
        return isExist(set, s2, 0, false);
    }

    private boolean isExist(Map<Character, Integer> set, String s2, int begin, boolean hasFind) {
        if (set.size() == 0) {
            return true;
        }
        if (s2.length() - begin < set.size()) {
            return false;
        }
        for (int i = begin; i < s2.length(); i++) {
            if (set.containsKey(s2.charAt(i))) {
                changeMap(set, s2.charAt(i), -1);
                if (isExist(set, s2, i + 1, true)) {
                    return true;
                } else {
                    changeMap(set, s2.charAt(i), 1);
                    if (hasFind) {
                        return false;
                    }
                }
            } else if (hasFind) {
                return false;
            }
        }
        return false;
    }

    private void changeMap(Map<Character, Integer> set, char c, int diff) {
        Integer previous = set.remove(c);
        if (previous != null) {
            if (previous + diff != 0) {
                set.put(c, previous + diff);
            }
        } else {
            if (diff > 0) {
                set.put(c, diff);
            } else {
                throw new RuntimeException("Less than zero!");
            }
        }
    }

}
