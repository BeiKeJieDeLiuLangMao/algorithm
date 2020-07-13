package bbm.leetcode.bytedance.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 *
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 *
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1034 {
    public static void main(String[] args) {
        System.out.println(new Array1034().maxAreaOfIsland(new int[][] {
            new int[] {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            new int[] {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[] {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            new int[] {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        }));
    }

    /**
     * 遍历整个矩阵，如果发现了陆地，就以此为起点，向上下左右方向深搜，我使用了一个 set 来保存搜索过的点，避免重复搜索
     */
    public int maxAreaOfIsland(int[][] grid) {
        Set<String> checked = new HashSet<>();
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String identify = i + ":" + j;
                if (grid[i][j] == 1 && !checked.contains(identify)) {
                    max = Math.max(max, getArea(checked, grid, i, j));
                }
            }
        }
        return max;
    }

    private int getArea(Set<String> checked, int[][] grid, int i, int j) {
        String identify = i + ":" + j;
        if (!checked.contains(identify)) {
            checked.add(identify);
            int size = 1;
            if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                size += getArea(checked, grid, i - 1, j);
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                size += getArea(checked, grid, i, j - 1);
            }
            if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                size += getArea(checked, grid, i + 1, j);
            }
            if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
                size += getArea(checked, grid, i, j + 1);
            }
            return size;
        } else {
            return 0;
        }
    }
}
