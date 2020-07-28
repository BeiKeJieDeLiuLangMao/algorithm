package bbm.leetcode.question;

import java.util.Arrays;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question3 {
    public static void main(String[] args) {
        System.out.println(new Question3().lengthOfLongestSubstring("abcabcbb"));
    }

    /**
     * 用两层循环来遍历所有数据，第一层循环表示起始下标，第二轮循环表示从末尾下标，虽然两层循环，但是实际上复杂度还是n，
     * 这里我们用一个桶来描述一个字符是否出现过，
     * 在第二轮循环中如果发现当前字符在桶中从没出现过，就将当前下标记录进去，否则，就用当前下标和之前出现过的下标相减得到不重复字符串的距离
     * 同时，我们也要修改第一轮循环地 index，让其跳到第二层循环的当前索引值之后
     * 在这整个过程中所搜索到的所有不重复字符串，我们取它们的最大长度作为结果输出
     */
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

    /**
     * 这个方案中，我们对上一个实现方案进行了简化，同样使用桶来描述一个符号出现的下标，这里我们在遍历每一个符号时，我们都将该符号之前出现的 index + 1
     * 作为 start，然后计算当前字符 i - start + 1 是否是最长的不重复字符串，是的话就保存结果，然后用当前扫描的字符下标替换桶中之前保存的值
     * 重复上述过程最终我们就得到了最长的不重复字符串
     */
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
}
