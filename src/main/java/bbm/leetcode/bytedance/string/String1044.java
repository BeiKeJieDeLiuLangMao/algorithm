package bbm.leetcode.bytedance.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author bbm
 * @date 2020/7/12
 */
public class String1044 {
    public static void main(String[] args) {
        System.out.println(new String1044().restoreIpAddresses(""));
    }

    /**
     * 我的思路是: 三个循环遍历所有分隔符的位置，满足情况就添加到结果集
     */
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4) {
            return new ArrayList<>();
        }
        LinkedList<String> result = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            String str0 = s.substring(0, i);
            if (str0.length() > 1 && str0.charAt(0) == '0') {
                break;
            }
            int part1 = Integer.parseInt(s.substring(0, i));
            int leftChar1 = s.length() - i;
            if (part1 > 255 || leftChar1 > 9 || leftChar1 < 3) {
                continue;
            }
            for (int j = 1; j <= 3; j++) {
                String str = s.substring(i, i + j);
                if (str.length() > 1 && str.charAt(0) == '0') {
                    break;
                }
                int part2 = Integer.parseInt(str);
                int leftChar2 = s.length() - i - j;
                if (part2 > 255 || leftChar2 > 6 || leftChar2 < 2) {
                    continue;
                }
                for (int k = 1; k <= 3 && i + j + k < s.length(); k++) {
                    String str1 = s.substring(i + j, i + j + k);
                    if (str1.length() > 1 && str1.charAt(0) == '0') {
                        break;
                    }
                    int part3 = Integer.parseInt(str1);
                    int leftChar3 = s.length() - i - j - k;
                    if (part3 > 255 || leftChar3 > 3 || leftChar3 < 1) {
                        continue;
                    }
                    String str2 = s.substring(i + j + k);
                    if (str2.length() > 1 && str2.charAt(0) == '0') {
                        continue;
                    }
                    int part4 = Integer.parseInt(s.substring(i + j + k));
                    if (part4 <= 255) {
                        result.add(part1 + "." + part2 + "." + part3 + "." + part4);
                    }
                }
            }
        }
        return result;
    }
}
