package bbm.leetcode.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question49 {
    public static void main(String[] args) {
        print(new Question49().groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<WorkKey, List<String>> workKeyMap = new HashMap<>();
        for (String str : strs) {
            WorkKey workKey = new WorkKey(str);
            if (workKeyMap.containsKey(workKey)) {
                workKeyMap.get(workKey).add(str);
            } else {
                workKeyMap.put(workKey, new ArrayList<String>() {{
                    add(str);
                }});
            }
        }
        workKeyMap.forEach((key, value) -> result.add(value));
        return result;
    }

    private static class WorkKey {
        int[] table = new int[26];
        private String sign;

        private WorkKey(String word) {
            for (char c : word.toCharArray()) {
                table[c - 'a']++;
            }
            StringBuilder signBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (table[i] > 0) {
                    signBuilder.append('a' + i);
                    signBuilder.append(table[i]);
                }
            }
            sign = signBuilder.toString();
        }

        @Override
        public int hashCode() {
            return sign.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof WorkKey) {
                return ((WorkKey) obj).sign.equals(sign);
            } else {
                return false;
            }
        }
    }
}
