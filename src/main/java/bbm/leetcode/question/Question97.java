package bbm.leetcode.question;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 *
 * @author bbm
 * @date 2020/7/20
 */
public class Question97 {

    public static void main(String[] args) {
        System.out.println(new Question97().isInterleave3("aabcc", "dbbca", "aadbbcbcac"));
    }

    /**
     * 双指针分支计算，超时
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave(s1, 0, s2, 0, s3, 0);
    }

    private boolean isInterleave(String s1, int index1, String s2, int index2, String s3, int index3) {
        if (index3 < s3.length()) {
            if (index1 < s1.length() && index2 < s2.length()) {
                if (s1.charAt(index1) == s2.charAt(index2)) {
                    if (s1.charAt(index1) != s3.charAt(index3)) {
                        return false;
                    } else {
                        return isInterleave(s1, index1 + 1, s2, index2, s3, index3 + 1)
                            || isInterleave(s1, index1, s2, index2 + 1, s3, index3 + 1);
                    }
                } else if (s1.charAt(index1) == s3.charAt(index3)) {
                    return isInterleave(s1, index1 + 1, s2, index2, s3, index3 + 1);
                } else if (s2.charAt(index2) == s3.charAt(index3)) {
                    return isInterleave(s1, index1, s2, index2 + 1, s3, index3 + 1);
                } else {
                    return false;
                }
            } else if (index1 < s1.length()) {
                if (s1.charAt(index1) != s3.charAt(index3)) {
                    return false;
                } else {
                    return isInterleave(s1, index1 + 1, s2, index2, s3, index3 + 1);
                }
            } else if (index2 < s2.length()) {
                if (s2.charAt(index2) != s3.charAt(index3)) {
                    return false;
                } else {
                    return isInterleave(s1, index1, s2, index2 + 1, s3, index3 + 1);
                }
            } else {
                return false;
            }
        } else {
            return index1 == s1.length() && index2 == s2.length();
        }
    }

    /**
     * 使用动态规划的思想，dp[i][j] 表示 s1 的 index 为 i，s2 的 index 为 j 时能否组成 s3 的长度为 i + j 的前缀
     * 对于每一个 dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1]) || (dp[i][j-1] && s2[j-1] == s3[i+j-1])
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] |= dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[i][j] |= dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 这里也是动态规划的思想，但是我们通过滚动数组将二维数组转化为一维数组，仔细观察二维数组的计算规则：dp[i][j] |= dp[i-1][j] && s1.charAt(i-1) == s3.charAt(p);
     * 它实际上是通过当前 j 下标的上一层 i 的结果直接计算得到的，所以可以转化为 dp[j] &= s1.charAt(i-1) == s3.charAt(p);
     * 而 dp[i][j] |= dp[i][j-1] && s2.charAt(j-1) == s3.charAt(p); 相当于计算了当前层的 j - 1 槽位，所以可以转变为 dp[j] |= dp[j-1] && s2.charAt(j-1) == s3.charAt(p);
     * 通过上述的 dp 计算方式转变，我们就将二维 dp 数据转化为了一维数据
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] &= s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] |= dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p);
                }
            }
        }
        return dp[s2.length()];
    }
}
