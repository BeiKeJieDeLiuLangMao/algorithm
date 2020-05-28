package bbm.leetcode;

import java.util.HashSet;
import java.util.Set;

import static bbm.leetcode.Utils.print;

/**
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *  
 *
 * 进阶：
 *
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question289 {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int[][] dp = new int[board.length][board[0].length];
        boolean init = false;
        Set<Data> alive = new HashSet<>();
        Set<Data> dead = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!init) {
                    init = true;
                    int sum = 0;
                    boolean jIsRightest = (j + 1 == board[i].length);
                    if (!jIsRightest) {
                        sum += board[i][j + 1];
                    }
                    if (i + 1 < board.length) {
                        sum += board[i + 1][j];
                        if (!jIsRightest) {
                            sum += board[i + 1][j + 1];
                        }
                    }
                    handleDp(board, dp, alive, dead, i, j, sum);
                } else if (j != 0) {
                    int sum = dp[i][j - 1];

                    boolean jIsRightest = (j + 1 == board[i].length);
                    if (i >= 1) {
                        if (j >= 2) {
                            sum -= board[i - 1][j - 2];
                        }
                        if (!jIsRightest) {
                            sum += board[i - 1][j + 1];
                        }
                    }
                    if (j >= 2) {
                        sum -= board[i][j - 2];
                    }
                    sum += board[i][j - 1];
                    sum -= board[i][j];
                    if (!jIsRightest) {
                        sum += board[i][j + 1];
                    }
                    if (i + 1 < board.length) {
                        if (j >= 2) {
                            sum -= board[i + 1][j - 2];
                        }
                        if (!jIsRightest) {
                            sum += board[i + 1][j + 1];
                        }
                    }
                    handleDp(board, dp, alive, dead, i, j, sum);
                } else {
                    int sum = dp[i - 1][0];
                    boolean jIsRightest = (j + 1 == board[i].length);
                    if (i >= 2) {
                        sum -= board[i - 2][j];
                        if (!jIsRightest) {
                            sum -= board[i - 2][j + 1];
                        }
                    }
                    sum += board[i - 1][j];
                    sum -= board[i][j];
                    if (i + 1 < board.length) {
                        sum += board[i + 1][j];
                        if (!jIsRightest) {
                            sum += board[i + 1][j + 1];
                        }
                    }
                    handleDp(board, dp, alive, dead, i, j, sum);
                }
            }
        }
        dead.forEach(data -> board[data.i][data.j] = 0);
        alive.forEach(data -> board[data.i][data.j] = 1);
    }

    private void handleDp(int[][] board, int[][] dp, Set<Data> alive, Set<Data> dead, int i, int j, int sum) {
        dp[i][j] = sum;
        if (dp[i][j] < 2) {
            // dead
            if (board[i][j] == 1) {
                dead.add(new Data(i, j));
            }
        } else if (dp[i][j] == 2) {
            // keep
        } else if (dp[i][j] == 3) {
            // alive
            if (board[i][j] == 0) {
                alive.add(new Data(i, j));
            }
        } else {
            // dead
            if (board[i][j] == 1) {
                dead.add(new Data(i, j));
            }
        }
    }

    private static class Data {
        int i;
        int j;

        private Data(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }
    }

    /**
     * 用 int 的倒数第二位存储最终值
     */
    public void gameOfLife2(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int sum = 0;
                boolean jIsRightest = (j + 1 == board[i].length);
                if (i >= 1) {
                    if (j >= 1 && isAlive(board, i - 1, j - 1)) {
                        sum++;
                    }
                    if (isAlive(board, i - 1, j)) {
                        sum++;
                    }
                    if (!jIsRightest && isAlive(board, i - 1, j + 1)) {
                        sum++;
                    }
                }
                if (j >= 1 && isAlive(board, i, j - 1)) {
                    sum++;
                }
                if (!jIsRightest && isAlive(board, i, j + 1)) {
                    sum++;
                }
                if (i + 1 < board.length) {
                    if (j >= 1 && isAlive(board, i + 1, j - 1)) {
                        sum++;
                    }
                    if (isAlive(board, i + 1, j)) {
                        sum++;
                    }
                    if (!jIsRightest && isAlive(board, i + 1, j + 1)) {
                        sum++;
                    }
                }
                handleSum(board, sum, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    private void handleSum(int[][] board, int sum, int i, int j) {
        if (sum < 2) {
            // dead
        } else if (sum == 2) {
            // keep
            if (board[i][j] == 1) {
                board[i][j] = 0b11;
            }
        } else if (sum == 3) {
            // alive
            if (board[i][j] == 0) {
                board[i][j] = 0b10;
            } else {
                board[i][j] = 0b11;
            }
        } else {
            // dead
        }
    }

    private boolean isAlive(int[][] board, int i, int j) {
        return (board[i][j] & 0b1) == 1;
    }

    public static void main(String[] args) {
        int[][] data = new int[][] {
            new int[] {0, 1, 0},
            new int[] {0, 0, 1},
            new int[] {1, 1, 1},
            new int[] {0, 0, 0}};
        new Question289().gameOfLife2(data);
        print(data);
    }
}
