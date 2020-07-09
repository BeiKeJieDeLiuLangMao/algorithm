package bbm.leetcode.question;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question202 {
    public static void main(String[] args) {
        System.out.println(new Question202().isHappy(20));
    }

    public boolean isHappy(int n) {
        Set<Integer> exist = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            exist.add(n);
            do {
                int temp = n % 10;
                sum += (int) Math.pow(temp, 2);
                n = n / 10;
            }
            while (n != 0);
            n = sum;
            if (exist.contains(n)) {
                return false;
            }
        }
        return true;
    }
}
