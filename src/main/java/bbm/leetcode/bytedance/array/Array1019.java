package bbm.leetcode.bytedance.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1019 {
    public static void main(String[] args) {
        System.out.println(new Array1019().longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> allNums = new HashSet<>();
        for (int num : nums) {
            allNums.add(num);
        }
        Map<Integer, Integer> startNumberMap = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, getMaxLength(allNums, startNumberMap, num));
        }
        return max;
    }

    public int getMaxLength(Set<Integer> allNums, Map<Integer, Integer> startNumberMap, int num) {
        if (allNums.contains(num)) {
            int length = 1;
            if (startNumberMap.containsKey(num + 1)) {
                length += startNumberMap.get(num + 1);
            } else {
                length += getMaxLength(allNums, startNumberMap, num + 1);
            }
            startNumberMap.put(num, length);
            return length;
        } else {
            return 0;
        }
    }
}
