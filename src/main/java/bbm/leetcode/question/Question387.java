package bbm.leetcode.question;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question387 {
    public static void main(String[] args) {
        System.out.println(new Question387().firstUniqChar("leetcode"));
    }

    public int firstUniqChar(String s) {
        int[] chars = new int[123];
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i)] <= 1) {
                chars[s.charAt(i)]++;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
