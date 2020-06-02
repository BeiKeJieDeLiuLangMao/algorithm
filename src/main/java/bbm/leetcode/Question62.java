package bbm.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *  
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question62 {
    public static void main(String[] args) {
        System.out.println(new Question62().uniquePaths(51, 9));
    }

    public int uniquePaths(int m, int n) {
        int[][] memo = new int[n][m];
        memo[n - 1][m - 1] = 1;
        Queue<Data> queue1 = new LinkedList<>();
        Queue<Data> queue2 = new LinkedList<>();
        queue1.add(new Data(n - 1, m - 1));
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            Queue<Data> from;
            Queue<Data> to;
            if (!queue1.isEmpty()) {
                from = queue1;
                to = queue2;
            } else {
                from = queue2;
                to = queue1;
            }
            Set<Data> data2Add = new HashSet<>();
            while (!from.isEmpty()) {
                Data data = from.poll();
                if (data.col - 1 >= 0) {
                    int row = data.row;
                    int col = data.col - 1;
                    int sum = computeSum(memo, row, col, n, m);
                    memo[row][col] = sum;
                    data2Add.add(new Data(row, col));
                }
                if (data.row - 1 >= 0) {
                    int row = data.row - 1;
                    int col = data.col;
                    int sum = computeSum(memo, row, col, n, m);
                    memo[row][col] = sum;
                    data2Add.add(new Data(row, col));
                }
                if (data.col - 1 >= 0 && data.row - 1 >= 0) {
                    int row = data.row - 1;
                    int col = data.col - 1;
                    int sum = computeSum(memo, row, col, n, m);
                    memo[row][col] = sum;
                    data2Add.add(new Data(row, col));
                }
            }
            to.addAll(data2Add);
        }
        return memo[0][0];
    }

    private int computeSum(int[][] memo, int row, int col, int rowLength, int colLength) {
        int sum = 0;
        if (row + 1 < rowLength) {
            sum += memo[row + 1][col];
        }
        if (col + 1 < colLength) {
            sum += memo[row][col + 1];
        }
        return sum;
    }

    private static class Data {
        private int row;
        private int col;

        private Data(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(row) + Objects.hashCode(col);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Data) {
                return ((Data) o).row == row && ((Data) o).col == col;
            } else {
                return false;
            }
        }
    }
}
