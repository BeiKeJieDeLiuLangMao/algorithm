package bbm.leetcode;

import java.util.Arrays;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 *
 * @author bbm
 * @date 2020/5/29
 */
public class Question179 {
    public static void main(String[] args) {
        System.out.println(new Question179().largestNumber(new int[]{10, 2}));
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        if (max == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        Arrays.stream(nums).mapToObj(String::valueOf).sorted(this::compare).forEach(result::append);
        return result.toString();
    }

    int compare(String o1, String o2) {
        int left = 0;
        int right = 0;
        int diff;
        while (left < o1.length() && right < o2.length()) {
            diff = o2.charAt(left) - o1.charAt(right);
            if (diff != 0) {
                return diff;
            }
            left++;
            right++;
        }
        if (left == o1.length() && right == o2.length()) {
            return 0;
        } else if (left == o1.length()) {
            return compare(o1, o2.substring(right));
        } else {
            return compare(o1.substring(left), o2);
        }
    }

    public String largestNumber2(int[] nums) {
        int zero = 0;
        if (nums[0] == 0) {
            zero++;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
            }
            int temp = nums[i], j = i - 1;
            for (; j >= 0; j--) {
                if (compare(nums[j], temp) < 0) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = temp;
        }
        if (zero == nums.length) {
            return "0";
        }
        // System.out.println(Arrays.toString(nums));
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    public int compare(int a, int b) {
        if (a == b) {
            return 0;
        }
        if (a == 0) {
            return -1;
        }
        if (b == 0) {
            return 1;
        }
        long lenA = 1, lenB = 1;
        int temp = a;
        while (temp > 0) {
            temp /= 10;
            lenA *= 10;
        }
        temp = b;
        while (temp > 0) {
            temp /= 10;
            lenB *= 10;
        }
        // System.out.println("a="+a+" b="+b+" , ab="+(a*lenB+b)+" , ba="+(b*lenA+a));
        if (a * lenB + b > b * lenA + a) {
            return 1;
        } else {
            return -1;
        }
    }
}
