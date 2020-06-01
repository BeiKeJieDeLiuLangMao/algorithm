package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 *
 * @author bbm
 * @date 2020/5/22
 */
public class Question66 {
    public static void main(String[] args) {
        print(new Question66().plusOne(new int[]{1, 2, 3, 4}));
    }

    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        for (; i >= 0; i--) {
            digits[i]++;
            if (digits[i] < 10) {
                break;
            } else {
                digits[i] = digits[i] % 10;
            }
        }
        int[] result;
        if (i < 0) {
            result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, digits.length);
        } else {
            result = digits;
        }
        return result;
    }
}
