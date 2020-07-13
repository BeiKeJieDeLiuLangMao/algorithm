package bbm.leetcode.bytedance.dp;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Dp1030 {
    public static void main(String[] args) {
        System.out.println(new Dp1030().minimumTotal(Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        )));
    }

    /**
     * 从上往下逐层计算选上一层的左节点更近还是相同index 节点更近，最后统计最后一层的所有节点的最小值
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][];
        for (int i = 0; i < triangle.size(); i++) {
            dp[i] = new int[triangle.get(i).size()];
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0) {
                    dp[i][j] = triangle.get(i).get(j);
                } else {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                    } else {
                        dp[i][j] = Math.min(j < dp[i - 1].length ? dp[i - 1][j] : Integer.MAX_VALUE, dp[i - 1][j - 1]) + triangle.get(i).get(j);
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int[] lastLine = dp[triangle.size() - 1];
        for (int i = 0; i < lastLine.length; i++) {
            if (min > lastLine[i]) {
                min = lastLine[i];
            }
        }
        return min;
    }
}
