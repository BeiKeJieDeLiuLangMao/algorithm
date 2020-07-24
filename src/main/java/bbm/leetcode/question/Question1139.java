package bbm.leetcode.question;

/**
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 *
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-1-bordered-square
 *
 * @author bbm
 * @date 2020/7/20
 */
public class Question1139 {
    public static void main(String[] args) {
        System.out.println(new Question1139().largest1BorderedSquare(new int[][] {
            new int[] {0, 1, 1, 1, 1, 0},
            new int[] {1, 1, 0, 1, 1, 0},
            new int[] {1, 1, 0, 1, 0, 1},
            new int[] {1, 1, 0, 1, 1, 1},
            new int[] {1, 1, 0, 1, 1, 1},
            new int[] {1, 1, 1, 1, 1, 1},
            new int[] {1, 0, 1, 1, 1, 1},
            new int[] {0, 0, 1, 1, 1, 1},
            new int[] {1, 1, 1, 1, 1, 1}
        }));
    }

    /**
     * 思路：统计水平方向（gridH）和垂直方向（gridV）最长的连续 1 个数，然后以此为根据，遍历每个可能的右下角节点，该节点所能组成的最大正方形
     * 的边长 edgeLength 即为 Math.min(gridH[i][j], gridV[i][j])，接下来我们以 maxEdgeLength 递减，看看当前 edgeLength 下，
     * 另外两条边是否也满足条件
     */
    public int largest1BorderedSquare(int[][] grid) {
        int[][] gridH = new int[grid.length][grid[0].length];
        int[][] gridV = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    gridH[i][j] = j > 0 ? gridH[i][j - 1] + 1 : 1;
                    gridV[i][j] = i > 0 ? gridV[i - 1][j] + 1 : 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int edgeLength = Math.min(gridH[i][j], gridV[i][j]);
                while (edgeLength > max) {
                    if (gridV[i][j - edgeLength + 1] >= edgeLength && gridH[i - edgeLength + 1][j] >= edgeLength) {
                        max = edgeLength;
                        break;
                    } else {
                        edgeLength--;
                    }
                }
            }
        }
        return max * max;
    }
}
