package bbm.leetcode;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 *
 * @author bbm
 * @date 2020/5/22
 */
public class Question125 {
    public static void main(String[] args) {
        System.out.println(new Question125().isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        for (int left = 0, right = s.length() - 1; left < right; ) {
            char leftChar = s.charAt(left);
            if (leftChar >= 65 && leftChar <= 90) {
                leftChar += 32;
            }
            char rightChar = s.charAt(right);
            if (rightChar >= 65 && rightChar <= 90) {
                rightChar += 32;
            }
            if (!((leftChar >= 97 && leftChar <= 122) || (leftChar >= 48 && leftChar <= 57))) {
                left++;
                continue;
            }
            if (!((rightChar >= 97 && rightChar <= 122) || (rightChar >= 48 && rightChar <= 57))) {
                right--;
                continue;
            }
            if (leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
