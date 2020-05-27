package bbm.leetcode;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.function.ToIntFunction;

import static bbm.leetcode.Utils.print;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(value -> value[0]));
        int leftIndex = -1;
        int rightIndex = -1;
        boolean finished = false;
        int leftLength = intervals.length;
        while (leftLength > 1 && !finished) {
            boolean find = false;
            for (int i = 0; i < leftLength - 1; i++) {
                if (intervals[i][1] >= intervals[i+1][0]) {
                    leftIndex = i;
                    rightIndex = i+1;
                    find = true;
                    break;
                }
            }
            if (find) {
                int smallIndex = Math.min(leftIndex, rightIndex);
                int largeIndex = Math.max(leftIndex, rightIndex);
                intervals[smallIndex][0] = Math.min(intervals[leftIndex][0], intervals[rightIndex][0]);
                intervals[smallIndex][1] = Math.max(intervals[leftIndex][1], intervals[rightIndex][1]);
                leftLength--;
                for (int i = largeIndex; i < leftLength; i++) {
                    intervals[i] = intervals[i + 1];
                }
            } else {
                finished = true;
            }
        }
        return Arrays.copyOf(intervals, leftLength);
    }

    public int[][] merge2(int[][] intervals) {
        int resNum = intervals.length;
        for (int i = 0; i < intervals.length - 1; i++)
            for (int j = i + 1; j < intervals.length; j++) {
                int max = Math.max(intervals[i][1], intervals[j][1]);
                int min = Math.min(intervals[i][0], intervals[j][0]);
                int iLen = intervals[i][1] - intervals[i][0];
                int jLen = intervals[j][1] - intervals[j][0];
                if (max - min <= iLen + jLen) {
                    intervals[j][0] = min;
                    intervals[j][1] = max;
                    intervals[i] = null;
                    resNum--;
                    break;
                }
            }

        int[][] res = new int[resNum][2];
        int i = 0;
        for (int[] x : intervals)
            if (x != null) {
                res[i] = x;
                i++;
            }
        return res;
    }

    public static void main(String[] args) {
        print(new Question56().merge2(new int[][] {new int[] {11, 12}, new int[] {5, 10}, new int[] {1, 2}, new int[] {2, 3}}));
    }
}
