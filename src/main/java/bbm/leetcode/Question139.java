package bbm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        return findWordBreak(s, 0, wordDict, new HashSet<>());
    }

    private boolean findWordBreak(String s, int start, List<String> wordDict, Set<String> wrongSet) {
        if (start == s.length()) {
            return true;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            if (s.startsWith(wordDict.get(i), start)) {
                if (!wrongSet.contains(s.substring(start + wordDict.get(i).length()))) {
                    if (findWordBreak(s, start + wordDict.get(i).length(), wordDict, wrongSet)) {
                        return true;
                    }
                }
            }
        }
        wrongSet.add(s.substring(start));
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Question139().wordBreak("leetcode", Arrays.stream(new String[] {"leet", "code"}).collect(Collectors.toList())));
    }
}
