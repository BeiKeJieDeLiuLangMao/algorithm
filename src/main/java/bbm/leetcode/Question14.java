package bbm.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question14 {
    public static void main(String[] args) {
        System.out.println(new Question14().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    public String longestCommonPrefix(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        if (strings.length == 1) {
            return strings[0];
        }
        shiftMin(strings);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings[0].length(); i++) {
            char c = strings[0].charAt(i);
            for (int j = 1; j < strings.length; j++) {
                if (strings[j].length() <= i || c != strings[j].charAt(i)) {
                    return result.toString();
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    private void shiftMin(String[] strings) {
        int min = strings.length;
        int minIndex = 0;
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].length() < min) {
                min = strings[i].length();
                minIndex = i;
            }
        }
        if (minIndex > 0) {
            String temp = strings[minIndex];
            strings[minIndex] = strings[0];
            strings[0] = temp;
        }
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        shiftMin(strs);
        //假设第一个字符串就是最长公共前缀
        String res = strs[0];
        //遍历字符串数组
        for (int i = 1; i < strs.length; i++) {
            //最长公共前缀子串res如果找到了，一定在strs[i]的起始位置，就是index=0的位置
            //如果没有找到就不为0
            while (strs[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
}
