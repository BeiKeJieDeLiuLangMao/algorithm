package bbm.leetcode;

import java.util.ArrayList;
import java.util.List;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question130 {
    public static void main(String[] args) {
        char[][] data = new char[][] {
            new char[] {'X', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O'},
            new char[] {'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
            new char[] {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X'},
            new char[] {'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
            new char[] {'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O'},
            new char[] {'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
            new char[] {'O', 'O', 'O', 'X', 'X', 'O', 'X', 'O', 'X'},
            new char[] {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O'},
            new char[] {'O', 'X', 'O', 'O', 'O', 'X', 'O', 'X', 'O'}
        };
        print(data);
        new Question130().solve(data);
        print(data);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '*';
            }
            if (board[i][board[i].length - 1] == 'O') {
                board[i][board[i].length - 1] = '*';
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') {
                board[0][j] = '*';
            }
            if (board[board.length - 1][j] == 'O') {
                board[board.length - 1][j] = '*';
            }
        }
        print(board);
        List<Data> path = new ArrayList<>();
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j] == 'O') {
                    findWayOut(path, board, i, j, true);
                    System.out.println("---" + i + ':' + j + "---");
                    print(board);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private boolean findWayOut(List<Data> path, char[][] board, int i, int j, boolean firstOne) {
        if (i == -1 || i >= board.length) {
            return true;
        }
        if (j == -1 || j >= board[0].length) {
            return true;
        }
        if (board[i][j] == '*') {
            return true;
        }
        board[i][j] = '?';
        path.add(new Data(i, j));
        boolean findWay = false;
        if (board[i - 1][j] != 'X' && board[i - 1][j] != '?' && findWayOut(path, board, i - 1, j, false)) {
            findWay = true;
        }
        if (board[i + 1][j] != 'X' && board[i + 1][j] != '?' && findWayOut(path, board, i + 1, j, false)) {
            findWay = true;
        }
        if (board[i][j - 1] != 'X' && board[i][j - 1] != '?' && findWayOut(path, board, i, j - 1, false)) {
            findWay = true;
        }
        if (board[i][j + 1] != 'X' && board[i][j + 1] != '?' && findWayOut(path, board, i, j + 1, false)) {
            findWay = true;
        }
        if (firstOne) {
            for (Data data : path) {
                board[data.i][data.j] = findWay ? '*' : 'X';
            }
            path.clear();
        }
        return findWay;
    }

    private static class Data {
        private int i;
        private int j;

        public Data(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
