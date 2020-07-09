package bbm.leetcode.bytedance;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author bbm
 * @date 2020/7/9
 */
public class String1015 {
    public static void main(String[] args) {
        System.out.println(new String1015().multiply("0", "0"));
    }

    public String multiply(String num1, String num2) {
        int[] num1Array = new int[num1.length()];
        int[] num2Array = new int[num2.length()];
        int[] result = new int[num1.length() + num2.length() + 1];
        for (int i = 0; i < num1.length(); i++) {
            num1Array[i] = num1.charAt(num1.length() - i - 1) - '0';
        }
        for (int i = 0; i < num2.length(); i++) {
            num2Array[i] = num2.charAt(num2.length() - i - 1) - '0';
        }
        Map<Integer, int[]> num1MultiResult = new HashMap<>();
        for (int num2Index = 0; num2Index < num2Array.length; num2Index++) {
            int num2Part = num2Array[num2Index];
            if (!num1MultiResult.containsKey(num2Part)) {
                int[] tempResult = new int[num1.length() + 1];
                for (int num1Index = 0; num1Index < num1Array.length; num1Index++) {
                    tempResult[num1Index] += num2Part * num1Array[num1Index];
                    int shiftIndex = num1Index;
                    while (tempResult[shiftIndex] > 9) {
                        tempResult[shiftIndex + 1] = tempResult[shiftIndex] / 10;
                        tempResult[shiftIndex] = tempResult[shiftIndex] % 10;
                        shiftIndex++;
                    }
                }
                num1MultiResult.put(num2Part, tempResult);
            }
            int[] tempResult = num1MultiResult.get(num2Part);
            for (int index = 0; index < tempResult.length; index++) {
                result[num2Index + index] += tempResult[index];
                int shiftIndex = num2Index + index;
                while (result[shiftIndex] > 9) {
                    result[shiftIndex + 1] += result[shiftIndex] / 10;
                    result[shiftIndex] = result[shiftIndex] % 10;
                    shiftIndex++;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        int first = result.length - 1;
        while (first >= 0 && result[first] == 0) {
            first--;
        }
        for (int i = first; i >= 0; i--) {
            builder.append(result[i]);
        }
        return builder.length() > 0 ? builder.toString() : "0";
    }
}
