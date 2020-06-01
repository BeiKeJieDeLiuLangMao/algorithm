package bbm.leetcode;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 *
 * @author bbm
 * @date 30/5/2020
 */
public class Question91 {
    public static void main(String[] args) {
        System.out.println(new Question91().numDecodings2("17"));
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return "0".equals(s) ? 0 : 1;
        }
        Data max = new Data();
        decode(max, s.toCharArray(), 0);
        return max.max;
    }

    private void decode(Data max, char[] data, int start) {
        if (start >= data.length) {
            max.max++;
        } else {
            if (start == data.length - 1) {
                if (data[start] - '0' != 0) {
                    max.max++;
                }
            } else {
                if (start + 1 < data.length) {
                    int left = data[start] - '0';
                    if (left != 0) {
                        if (left == 1) {
                            decode(max, data, start + 2);
                        } else if (left == 2 && data[start + 1] - '0' <= 6) {
                            decode(max, data, start + 2);
                        }
                    }
                }
                if (data[start] - '0' != 0) {
                    decode(max, data, start + 1);
                }
            }
        }
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return "0".equals(s) ? 0 : 1;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i - 1 > 0) {
                int left = s.charAt(i - 2) - '0';
                if (left != 0) {
                    if (left == 1) {
                        dp[i] += dp[i - 2];
                    } else if (left == 2 && s.charAt(i - 1) - '0' <= 6) {
                        dp[i] += dp[i - 2];
                    }
                }
            }
            if (s.charAt(i - 1) - '0' != 0) {
                dp[i] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }

    private static class Data {
        private int max;
    }
}
