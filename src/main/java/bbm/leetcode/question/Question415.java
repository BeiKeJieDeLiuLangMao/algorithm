package bbm.leetcode.question;

/**
 * @author bbm
 * @date 2020/8/3
 */
public class Question415 {
    public static void main(String[] args) {
        System.out.println(new Question415().addStrings("98", "9"));
    }

    /**
     * 用一个 int 数组 result 保存目标数据，每一个槽位对应了一位，计算完一位之后，对该位进行进位，最后将 result 的结果转化为一个 String
     */
    public String addStrings(String num1, String num2) {
        char[] num1Chars = num1.toCharArray();
        char[] num2Chars = num2.toCharArray();
        int[] result = new int[Math.max(num1.length(), num2.length()) + 1];
        for (int i = 0; i < result.length - 1; i++) {
            result[i] += (i < num1Chars.length ? (num1Chars[num1Chars.length - i - 1] + -'0') : 0)
                + (i < num2Chars.length ? (num2Chars[num2Chars.length - i - 1] + -'0') : 0);
            result[i + 1] = result[i] / 10;
            result[i] = result[i] % 10;
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = result.length - 1; i >= 0; i--) {
            if (i == result.length - 1) {
                if (result[i] != 0) {
                    resultBuilder.append(result[i]);
                }
            } else {
                resultBuilder.append(result[i]);
            }
        }
        return resultBuilder.toString();
    }
}
