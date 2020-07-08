package bbm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个数字数组 arr 。
 *
 * 如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
 *
 * 如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,5,1]
 * 输出：true
 * 解释：对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
 * 示例 2：
 *
 * 输入：arr = [1,2,4]
 * 输出：false
 * 解释：无法通过重新排序得到等差数列。
 *  
 *
 * 提示：
 *
 * 2 <= arr.length <= 1000
 * -10^6 <= arr[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-make-arithmetic-progression-from-sequence
 *
 * @author bbm
 * @date 2020/7/8
 */
public class Question1502 {

    public static void main(String[] args) {
        System.out.println(new Question1502().canMakeArithmeticProgression(new int[] {
            1, 2, 4
        }));
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>(arr.length * 2);
        boolean hasSameItem = false;
        for (int d : arr) {
            if (d > max) {
                max = d;
            }
            if (d < min) {
                min = d;
            }
            if (set.contains(d)) {
                hasSameItem = true;
            } else {
                set.add(d);
            }
        }
        if (min == max) {
            return true;
        }
        if (hasSameItem) {
            return false;
        }
        int diff = (max - min) / (set.size() - 1);
        for (int i = min; i < max; i += diff) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
