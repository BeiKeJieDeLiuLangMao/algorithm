package bbm.leetcode.question;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 *
 * @author bbm
 * @date 2020/7/6
 */
public class Question63 {
    public static void main(String[] args) {
        System.out.println(new Question63().uniquePathsWithObstacles(new int[][] {
            new int[] {0, 0, 0},
            new int[] {0, 1, 0},
            new int[] {0, 0, 0},
        }));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = i == 0 ? 1 : 0; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    int left = 0, top = 0;
                    if (i - 1 >= 0) {
                        left = dp[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        top = dp[i][j - 1];
                    }
                    dp[i][j] = left + top;
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[obstacleGrid.length - 1].length - 1];
    }
}
