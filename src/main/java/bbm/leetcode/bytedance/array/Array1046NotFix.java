package bbm.leetcode.bytedance.array;

import static bbm.leetcode.common.Utils.print;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1046NotFix {
    public static void main(String[] args) {
        print(new Array1046NotFix().merge(new int[][] {
            new int[] {1, 3},
            new int[] {2, 6},
            new int[] {8, 10},
            new int[] {15, 18}
        }));
    }

    public int[][] merge(int[][] intervals) {
        /*TreeMap<Integer, Integer> beginMap = new TreeMap<>();
        for (int[] area : intervals) {
            int begin = area[0];
            int end = area[1];
            Map.Entry<Integer, Integer> entry = beginMap.ceilingEntry(begin);
            if (entry != null && entry.getValue() < end) {
                beginMap.put(entry.getKey(), Math.max(end, entry.getValue()));
                continue;
            }
            entry = beginMap.floorEntry(end);
            if (entry != null && entry.getKey() > begin) {
                beginMap.remove(entry);
                beginMap.put()
            }
        }*/
        return null;
    }
}
