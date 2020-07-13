package bbm.leetcode.bytedance.dp;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Dp1028 {
    public static void main(String[] args) {
        System.out.println(new Dp1028().maximalSquare(new char[][] {
            new char[] {'1', '0', '1', '0', '0'},
            new char[] {'1', '0', '1', '1', '1'},
            new char[] {'1', '1', '1', '1', '1'},
            new char[] {'1', '0', '0', '1', '0'}
        }));
    }

    /**
     * 最左列和最上行的正方形最大边长为 1，而对于其他节点，我们直接看左边的节点和上边的节点的最大正方形边长大小，选取最小的作为参考值 x，
     * 我们以这个参考值查看 [i-x][j-x] 是不是 1 是的话，当前节点的最大正方形边长就是 x + 1,否则是 x
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] map = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                map[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    if (j - 1 < 0 || i - 1 < 0) {
                        map[i][j] = 1;
                    } else {
                        int leftTopMin = Math.min(map[i][j - 1], map[i - 1][j]);
                        if (leftTopMin > 0) {
                            if (matrix[i - leftTopMin][j - leftTopMin] != '1') {
                                leftTopMin--;
                            }
                        }
                        map[i][j] = 1 + leftTopMin;
                    }
                    max = Math.max(max, map[i][j]);
                }
            }
        }
        return max * max;
    }
}
