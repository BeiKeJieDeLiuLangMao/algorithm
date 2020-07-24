package bbm.leetcode.question;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 *
 * @author bbm
 * @date 2020/7/23
 */
public class Question64 {
    public static void main(String[] args) {
        System.out.println(new Question64().minPathSum(new int[][] {
            new int[] {1, 2},
            new int[] {1, 1},
        }));
    }

    /**
     * 这里的思路是用一个数组来描述当前轮的计算数据，这里本应该使用一个二维数组的但是可以通过滚动数组的思想进行优化，最终使用了一个一维数组
     * 首先我们要计算第一行到达各个格子所需要的最小数字和，即从左到右累加，而从第二行开始就要考虑是从左过来和从上下来哪边的数字和最小，我们选取小的
     * 然后加上当前这一个的数字，就能得到到达该格的最小值，重复的执行上述过程指导走到最右下角的格就得到了最终答案
     */
    public int minPathSum(int[][] grid) {
        int[] result = new int[grid[0].length + 1];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0) {
                    result[j + 1] = result[j] + grid[i][j];
                } else {
                    result[j + 1] = Math.min(result[j], result[j + 1]) + grid[i][j];
                }
            }
            if (i == 0) {
                result[0] = Integer.MAX_VALUE;
            }
        }
        return result[grid[0].length];
    }
}
