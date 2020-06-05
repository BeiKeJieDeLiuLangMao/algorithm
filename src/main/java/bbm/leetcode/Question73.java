package bbm.leetcode;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question73 {
    public static void main(String[] args) {
        int[][] data = new int[][] {
            new int[] {0, 1, 2, 0},
            new int[] {3, 4, 5, 2},
            new int[] {1, 3, 1, 5}
        };
        int i = 0b10000000000000000000000000000000;
        new Question73().setZeroes(data);
        print(data);
    }

    /**
     * 用找到第一个 0 所处的行和列来保存其他行列是否有0的信息，然后根据这个信息进行赋值
     */
    public void setZeroes(int[][] matrix) {
        int row = -1;
        int col = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1) {
                break;
            }
        }
        if (row != -1) {
            for (int i = 0; i < row; i++) {
                matrix[i][col] = 0;
            }
            for (int j = 0; j < col; j++) {
                matrix[row][j] = 0;
            }
            matrix[row][col] = 1;
            for (int i = row + 1; i < matrix.length; i++) {
                if (matrix[i][col] == 0) {
                    matrix[i][col] = 1;
                } else {
                    matrix[i][col] = 0;
                }
            }
            for (int j = col + 1; j < matrix[row].length; j++) {
                if (matrix[row][j] == 0) {
                    matrix[row][j] = 1;
                } else {
                    matrix[row][j] = 0;
                }
            }
            for (int i = row + 1; i < matrix.length; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[row][j] = 1;
                        matrix[i][col] = 1;
                    }
                }
            }
            for (int i = row + 1; i < matrix.length; i++) {
                for (int j = col + 1; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[row][j] = 1;
                        matrix[i][col] = 1;
                    }
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                if (i != row) {
                    if (matrix[i][col] == 1) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            matrix[i][j] = 0;
                        }
                    }
                }
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[row][j] == 1) {
                    for (int i = 0; i < matrix.length; i++) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }
}
