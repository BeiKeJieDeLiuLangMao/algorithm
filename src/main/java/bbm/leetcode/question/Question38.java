package bbm.leetcode.question;

/**
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * <p>
 * 注意：整数序列中的每一项将表示为一个字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question38 {
    public static void main(String[] args) {
        System.out.println(new Question38().countAndSay(6));
    }

    /**
     * 迭代
     */
    public String countAndSay(int n) {
        String base = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder nextBase = new StringBuilder();
            int position = 0;
            int j = 1;
            for (; j < base.length(); j++) {
                if (base.charAt(position) != base.charAt(j)) {
                    nextBase.append(j - position).append(base.charAt(position));
                    position = j;
                }
            }
            if (position != j) {
                nextBase.append(j - position).append(base.charAt(position));
            }
            base = nextBase.toString();
        }
        return base;
    }

    /**
     * 递归
     */
    public String countAndSay2(int n) {
        StringBuilder sb = new StringBuilder();
        int p = 0;
        int cur = 1;
        if (n == 1) {
            return "1";
        }
        String str = countAndSay2(n - 1);
        for (; cur < str.length(); cur++) {
            if (str.charAt(p) != str.charAt(cur)) {
                sb.append(cur - p).append(str.charAt(p));
                p = cur;
            }
        }
        if (p != cur) {
            sb.append(cur - p).append(str.charAt(p));
        }
        return sb.toString();
    }
}
