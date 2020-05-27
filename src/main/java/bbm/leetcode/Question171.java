package bbm.leetcode;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question171 {
    public int titleToNumber(String s) {
        int sum = 0;
        int level = 0;
        while (s.length() > 0) {
            char lastChar = s.charAt(s.length() - 1);
            s = s.substring(0, s.length() - 1);
            if (level > 0) {
                sum += Math.pow(26, level) * (lastChar - 'A' + 1);
            } else {
                sum += (lastChar - 'A' + 1);
            }
            level++;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Question171().titleToNumber("AAA"));
    }
}
